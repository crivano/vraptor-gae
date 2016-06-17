package com.crivano.vraptorgae.framework;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.core.DefaultInterceptorStack;
import br.com.caelum.vraptor.core.InterceptorHandler;
import br.com.caelum.vraptor.core.InterceptorHandlerFactory;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

/**
 * Default implementation of a interceptor stack.
 * 
 * @author guilherme silveira
 * 
 */
@Component
@PrototypeScoped
public class MyInterceptorStack implements InterceptorStack {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultInterceptorStack.class);

	private final LinkedList<InterceptorHandler> interceptors = new LinkedList<InterceptorHandler>();
	private final InterceptorHandlerFactory handlerFactory;

	public MyInterceptorStack(InterceptorHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	public void next(ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
		if (interceptors.isEmpty()) {
			logger.debug("All registered interceptors have been called. End of VRaptor Request Execution.");
			return;
		}
		InterceptorHandler handler = interceptors.poll();
		
		// To GAE, skip Flash
		//
		if (handler.toString().contains("FlashInterceptor"))
			handler = interceptors.poll();
		handler.execute(this, method, resourceInstance);
	}

	public void add(Class<? extends Interceptor> type) {
		this.interceptors.addLast(handlerFactory.handlerFor(type));
	}

	// XXX this method will be removed soon
	public void addAsNext(Class<? extends Interceptor> type) {
		if (!type.getPackage().getName()
				.startsWith("br.com.caelum.vraptor.interceptor")
				&& !type.equals(ForwardToDefaultViewInterceptor.class)) {
			this.interceptors.addFirst(handlerFactory.handlerFor(type));
		}
	}

	@Override
	public String toString() {
		return "DefaultInterceptorStack " + interceptors;
	}

}

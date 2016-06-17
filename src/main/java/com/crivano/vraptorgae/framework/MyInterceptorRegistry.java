package com.crivano.vraptorgae.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.InterceptorRegistry;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

//@Component
@ApplicationScoped
public class MyInterceptorRegistry implements InterceptorRegistry {
	private final List<Class<? extends Interceptor>> interceptors = new ArrayList<Class<? extends Interceptor>>();

	public void register(Class<? extends Interceptor>... interceptors) {
		this.interceptors.addAll(Arrays.asList(interceptors));
//		List<Class<? extends Interceptor>> list = Arrays.asList(interceptors);
//
//		for (Class<? extends Interceptor> clazz : list) {
//			//if (!clazz.equals(FlashInterceptor.class))
//				this.interceptors.add(clazz);
//		}
	}

	public List<Class<? extends Interceptor>> all() {
		return interceptors;
	}

}

package com.crivano.vraptorgae.framework;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.core.DefaultStaticContentHandler;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class MyStaticContentHandler extends DefaultStaticContentHandler {

	public MyStaticContentHandler(ServletContext context) {
		super(context);
	}

	@Override
	public boolean requestingStaticFile(HttpServletRequest request) throws MalformedURLException {
		if (request.getServletPath().contains("/_ah/admin"))
			return true;
		return super.requestingStaticFile(request);
	}

}
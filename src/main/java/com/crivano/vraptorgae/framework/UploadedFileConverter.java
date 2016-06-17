package com.crivano.vraptorgae.framework;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Convert(UploadedFile.class)
public class UploadedFileConverter implements Converter {

	private final HttpServletRequest request;

	public UploadedFileConverter(HttpServletRequest request) {
		this.request = request;
	}

	public Object convert(String value, Class type, ResourceBundle bundle) {
		Object upload = request.getAttribute(value);
		return upload == null ? null : type.cast(upload);
	}

}
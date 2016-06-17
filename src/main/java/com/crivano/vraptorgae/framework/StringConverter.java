package com.crivano.vraptorgae.framework;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

import com.crivano.vraptorgae.util.Util;

@Convert(String.class)
@ApplicationScoped
public class StringConverter implements Converter<String> {

	public String convert(String value, Class<? extends String> type,
			ResourceBundle bundle) {
		String s = Util.stringOrNull(value);
		if (s == null) {
			return null;
		}
		return s;
	}

}
package com.crivano.vraptorgae.framework;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

import com.crivano.vraptorgae.util.Util;
import com.googlecode.objectify.Key;

@Convert(Key.class)
@ApplicationScoped
public class ObjectifyKeyConverter implements Converter<Key> {

	public Key convert(String value, Class<? extends Key> type,
			ResourceBundle bundle) {
		String s = Util.stringOrNull(value);
		if (s == null) {
			return null;
		}
		return Key.create(s);
	}

}
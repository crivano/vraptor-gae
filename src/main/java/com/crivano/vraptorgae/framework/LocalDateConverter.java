package com.crivano.vraptorgae.framework;

import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

import com.crivano.vraptorgae.util.Util;

@Convert(LocalDate.class)
@ApplicationScoped
public class LocalDateConverter implements Converter<LocalDate> {

	public LocalDate convert(String value, Class<? extends LocalDate> type,
			ResourceBundle bundle) {
		String s = Util.stringOrNull(value);
		if (s == null) {
			return null;
		}

		try {
			return LocalDate.parse(s);
		} catch (IllegalArgumentException e) {
			throw new ConversionError("Data local inválida: " + s);
		}
	}

}
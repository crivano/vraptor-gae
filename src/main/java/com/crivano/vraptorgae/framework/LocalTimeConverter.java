package com.crivano.vraptorgae.framework;

import java.util.ResourceBundle;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.crivano.vraptorgae.util.Util;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(LocalDate.class)
@ApplicationScoped
public class LocalTimeConverter implements Converter<LocalTime> {

	public LocalTime convert(String value, Class<? extends LocalTime> type, ResourceBundle bundle) {
		String s = Util.stringOrNull(value);
		if (s == null) {
			return null;
		}

		try {
			return LocalTime.parse(s);
		} catch (IllegalArgumentException e) {
			throw new ConversionError("Hora local inválida: " + s);
		}
	}

}
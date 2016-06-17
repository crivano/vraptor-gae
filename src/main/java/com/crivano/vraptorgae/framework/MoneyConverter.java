package com.crivano.vraptorgae.framework;

import java.math.RoundingMode;
import java.util.ResourceBundle;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

import com.crivano.vraptorgae.util.Util;

@Convert(Money.class)
@ApplicationScoped
public class MoneyConverter implements Converter<Money> {

	public Money convert(String value, Class<? extends Money> type,
			ResourceBundle bundle) {
		String s = Util.stringOrNull(value);
		if (s == null) {
			return null;
		}

		try {
			return Money.of(CurrencyUnit.of("BRL"), Double.parseDouble(s),
					RoundingMode.HALF_UP);
		} catch (IllegalArgumentException e) {
			throw new ConversionError("Valor monetário inválido: " + s);
		}
	}

}
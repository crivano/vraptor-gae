package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import org.joda.money.Money;

import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class MoneySerializer implements JsonSerializer<Money> {

	public MoneySerializer() {
	}

	@Override
	public JsonElement serialize(Money src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.getAmount());
	}

}
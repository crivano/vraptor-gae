package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import org.joda.time.LocalDate;

import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class LocalDateSerializer implements JsonSerializer<LocalDate> {

	public LocalDateSerializer() {
	}

	@Override
	public JsonElement serialize(LocalDate src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.toString());
	}

}
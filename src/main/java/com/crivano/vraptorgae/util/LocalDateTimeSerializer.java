package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

	public LocalDateTimeSerializer() {
	}

	@Override
	public JsonElement serialize(LocalDateTime src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.toString());
	}

}
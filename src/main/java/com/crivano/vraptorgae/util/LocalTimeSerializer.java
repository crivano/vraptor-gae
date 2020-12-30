package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import org.joda.time.LocalTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class LocalTimeSerializer implements JsonSerializer<LocalTime> {

	public LocalTimeSerializer() {
	}

	@Override
	public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
		return context.serialize(src.toString("HH:mm"));
	}

}
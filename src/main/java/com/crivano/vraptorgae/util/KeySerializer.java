package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.googlecode.objectify.Key;

@SuppressWarnings("rawtypes")
@Component
public class KeySerializer implements JsonSerializer<Key> {

	public KeySerializer() {
	}

	@Override
	public JsonElement serialize(Key src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.getString());
	}

}
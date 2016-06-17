package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Type;

import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.Component;

import com.crivano.vraptorgae.framework.ObjectifyModel;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.googlecode.objectify.Key;

@SuppressWarnings("rawtypes")
@Component
public class KeyDeserializer implements JsonDeserializer<Key> {

	public KeyDeserializer() {
	}

	@SuppressWarnings("rawtypes")
	public Key deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) {

		Key key;

		try {
			if (json.isJsonPrimitive()) {
				String value = json.getAsString();
				if (value == null || value.trim().length() == 0)
					return null;
				key = Key.create(value);
				return key;
			}
			return null;
		} catch (JsonParseException e) {
			throw new ConversionError(
					"Invalid Json format to convert Key, expecting STRING: "
							+ e.getMessage());
		}
	}

}
package com.crivano.vraptorgae.util;

//package br.com.caelum.vraptor.serialization.gson.adapters;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.gson.VRaptorGsonBuilder;

import com.crivano.vraptorgae.framework.ObjectifyModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.googlecode.objectify.Key;

@Component
public class ObjectifyModelSerializer implements JsonSerializer<ObjectifyModel> {

	private VRaptorGsonBuilder builder;

	public ObjectifyModelSerializer() {
		// this.builder = builder;
	}

	@Override
	public JsonElement serialize(ObjectifyModel src, Type typeOfSrc,
			JsonSerializationContext context) {
//		Gson gson = null; // (Gson) (Object) context;
//		List<TypeAdapterFactory> factories = null;
//
//		Field[] fields = context.getClass().getDeclaredFields();
//		for (Field field : fields) {
//			field.setAccessible(true);
//			String name = field.getName();
//			try {
//				gson = (Gson) field.get(context);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}

		JsonObject jsonObject = new JsonObject();
		try {
			jsonObject.addProperty("key", Key.create(src).getString());
		} catch (Exception e) {
		}
		Field[] fields = src.getClass().getDeclaredFields();
		for (Field field : fields) {
			if ((field.getModifiers() & Modifier.STATIC) != 0)
				continue;
			field.setAccessible(true);
			String name = field.getName();
			try {
//				jsonObject.add(name, gson.toJsonTree(field.get(src)));
				jsonObject.add(name, context.serialize(field.get(src)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return jsonObject;
	}

}
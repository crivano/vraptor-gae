package com.crivano.vraptorgae.framework;

import java.util.Arrays;
import java.util.Collections;

import org.joda.money.Money;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.interceptor.DefaultTypeNameExtractor;
import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilderImpl;
import br.com.caelum.vraptor.serialization.xstream.XStreamConverters;

import com.crivano.vraptorgae.util.Util;
import com.googlecode.objectify.Key;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@PrototypeScoped
@Component
public class CustomXStreamBuilder extends XStreamBuilderImpl {

	public CustomXStreamBuilder(XStreamConverters converters,
			TypeNameExtractor extractor) {
		super(converters, extractor);
	}

	@Override
	public XStream jsonInstance() {
		XStream xstream = super.jsonInstance();
		xstream.aliasSystemAttribute(null, "class");
		xstream.aliasSystemAttribute(null, "resolves-to");
		xstream.registerConverter(new SingleValueConverter() {

			public boolean canConvert(Class clazz) {
				return clazz.isAssignableFrom(Money.class);
			}

			public Object fromString(String value) {
				String s = Util.stringOrNull(value);
				if (s == null) {
					return null;
				}

				s = "BRL " + s;

				try {
					return Money.parse(s.toUpperCase());
				} catch (IllegalArgumentException e) {
					throw new ConversionError("Valor monetario invalido: " + s);
				}
			}

			public String toString(Object o) {
				if (o == null)
					return null;
				String s = o.toString();
				if (s.startsWith("BRL "))
					s = s.substring(4);
				return s;
			}

		});
		xstream.registerConverter(new SingleValueConverter() {

			public boolean canConvert(Class clazz) {
				return clazz.isAssignableFrom(LocalDateTime.class);
			}

			public Object fromString(String value) {
				try {
					return LocalDateTime.parse(value);
				} catch (IllegalArgumentException e) {
					throw new ConversionError("Valor data e hora invalido: "
							+ value);
				}
			}

			public String toString(Object o) {
				if (o == null)
					return null;
				String s = o.toString();
				return s;
			}

		});
		xstream.registerConverter(new SingleValueConverter() {

			public boolean canConvert(Class clazz) {
				return clazz.isAssignableFrom(LocalDate.class);
			}

			public Object fromString(String value) {
				try {
					return LocalDate.parse(value);
				} catch (IllegalArgumentException e) {
					throw new ConversionError("Valor data e hora invalido: "
							+ value);
				}
			}

			public String toString(Object o) {
				if (o == null)
					return null;
				String s = o.toString();
				return s;
			}

		});
		xstream.registerConverter(new ReflectionConverter(xstream.getMapper(), xstream.getReflectionProvider()) {

			public boolean canConvert(Class clazz) {
				return ObjectifyModel.class.isAssignableFrom(clazz);
			}

			@Override
			public void marshal(Object obj, HierarchicalStreamWriter writer,
					MarshallingContext context) {
			    writer.startNode("key");
			    writer.setValue(Key.create(obj).getString());
			    writer.endNode();
			    
				super.marshal(obj, writer, context);
			}

		

		});
		return xstream;
	}

	@Override
	public XStream xmlInstance() {
		XStream xstream = super.xmlInstance();
		xstream.aliasSystemAttribute(null, "class");
		xstream.aliasSystemAttribute(null, "resolves-to");
		return xstream;
	}

	public static XStreamBuilder cleanInstance(Converter... converters) {
		return new CustomXStreamBuilder(new XStreamConverters(
				Arrays.asList(converters),
				Collections.<SingleValueConverter> emptyList()),
				new DefaultTypeNameExtractor());
	}
}
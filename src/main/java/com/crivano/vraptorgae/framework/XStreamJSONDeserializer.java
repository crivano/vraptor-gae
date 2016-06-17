package com.crivano.vraptorgae.framework;

import java.io.InputStream;

import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.resource.ResourceMethod;

//@Deserializes({ "application/json", "json" })
public class XStreamJSONDeserializer implements Deserializer {

	@Override
	public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
		return null;
	}

}

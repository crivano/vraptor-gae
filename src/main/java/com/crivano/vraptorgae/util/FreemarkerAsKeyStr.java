package com.crivano.vraptorgae.util;


import java.util.List;

import com.googlecode.objectify.Key;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerAsKeyStr implements TemplateMethodModel {

	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		Key key = (Key) args.get(0);
		return new SimpleScalar(key.getString());
	}
}
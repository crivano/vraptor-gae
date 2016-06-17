package com.crivano.vraptorgae.util;


import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerFetch implements TemplateMethodModel {

//	private BL bl;

	public TemplateModel exec(List args) throws TemplateModelException {
//		if (args.size() != 1) {
//			throw new TemplateModelException("Wrong arguments");
//		}
//		try {
//			return bl.get((Key) args.get(0));
//		} catch (EntityNotFoundException e) {
//			throw new TemplateModelException("Error fetching object from key.",
//					e);
//		}
		return null;
	}
}
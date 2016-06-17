package com.crivano.vraptorgae.util;


import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;

import freemarker.template.SimpleDate;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerAsDateTime implements TemplateMethodModel {

	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		DateTime dt = DateTime.parse((String) args.get(0));
		return new SimpleDate(new Timestamp(dt.getMillis()));
	}
}
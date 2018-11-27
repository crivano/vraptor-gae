package com.crivano.vraptorgae.framework;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.joda.money.Money;
import org.joda.money.format.MoneyAmountStyle;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.googlecode.objectify.ObjectifyFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

@Component
@ApplicationScoped
public class HtmlTemplate {
	private Configuration cfg;
	static HtmlTemplate instance;

	public HtmlTemplate(ObjectifyFactory factory) {
		instance = this;
	}

	@PostConstruct
	public void create() throws IOException {
		/* You should do this ONLY ONCE in the whole application life-cycle: */

		MoneyFormatterBuilder mcb = new MoneyFormatterBuilder();
		mcb.appendLiteral("R$");
		mcb.appendAmount(MoneyAmountStyle.ASCII_DECIMAL_COMMA_GROUP3_DOT);
		final MoneyFormatter mf = mcb.toFormatter();

		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("WEB-INF/template"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setObjectWrapper(new DefaultObjectWrapper() {
			@Override
			public TemplateModel wrap(final Object obj)
					throws TemplateModelException {
				if (obj == null) {
					return super.wrap(obj);
				}
				if (obj instanceof Money)
					return new SimpleScalar(mf.print((Money) obj));
				return super.wrap(obj);
			}
		});
	}

	public static String process(String template, Map<String, Object> parameters)
			throws IOException, TemplateException, MessagingException {

		/* Get the template (uses cache internally) */
		Template temp = instance.cfg.getTemplate(template + ".html");

		/* Merge data-model with template */
		Writer out = new StringWriter();
		temp.process(parameters, out);

		return out.toString();
	}
}
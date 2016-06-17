package com.crivano.vraptorgae.framework;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

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
public class Mailer {
	private Configuration cfg;

	public Mailer(ObjectifyFactory factory) {
	}

	@PostConstruct
	public void create() throws IOException {
		/* You should do this ONLY ONCE in the whole application life-cycle: */

		MoneyFormatterBuilder mcb = new MoneyFormatterBuilder();
		mcb.appendLiteral("R$");
		mcb.appendAmount(MoneyAmountStyle.ASCII_DECIMAL_COMMA_GROUP3_DOT);
		final MoneyFormatter mf = mcb.toFormatter();

		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("WEB-INF/email"));
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

	public void sendmail(String template, InternetAddress from,
			List<InternetAddress> to, List<InternetAddress> cc,
			List<InternetAddress> bcc, String subject,
			Map<String, Object> parameters) throws IOException,
			TemplateException, MessagingException {

		/* Get the template (uses cache internally) */
		Template temp = cfg.getTemplate(template + ".ftl");

		/* Merge data-model with template */
		Writer out = new StringWriter();
		temp.process(parameters, out);

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = out.toString();

		Message msg = new MimeMessage(session);
		msg.setFrom(from);
		for (InternetAddress ia : to)
			msg.addRecipient(Message.RecipientType.TO, ia);
		if (cc != null)
			for (InternetAddress ia : bcc)
				msg.addRecipient(Message.RecipientType.CC, ia);
		if (bcc != null)
			for (InternetAddress ia : bcc)
				msg.addRecipient(Message.RecipientType.BCC, ia);
		msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
		msg.setText(msgBody);
		Transport.send(msg);
	}
}
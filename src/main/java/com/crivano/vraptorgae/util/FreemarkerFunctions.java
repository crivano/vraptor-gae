package com.crivano.vraptorgae.util;

import java.io.Serializable;
import java.sql.Timestamp;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.ioc.Component;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

import freemarker.template.SimpleDate;

@Component
public class FreemarkerFunctions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8282613429006988191L;
	transient private Objectify ofy;

	public FreemarkerFunctions(Objectify ofy) {
		super();
		this.ofy = ofy;
	}

	public Object fetch(Key<Object> key) throws Exception {
		if (key == null)
			return null;
		return ofy.load().key(key).now();
	}

	public String keyStr(Object obj) {
		try {
			if (obj == null)
				return "";
			if (obj instanceof String)
				if (((String) obj).trim().length() == 0)
					return "";
			if (obj instanceof Key)
				return ((Key) obj).getString();
			return Key.create(obj).getString();
		} catch (java.lang.NullPointerException e) {
			return "";
		} catch (java.lang.IllegalArgumentException e) {
			return "";
		}
	}

	public SimpleDate asDateTime(Object obj) {
		String s = (String) obj;
		s = Util.stringOrNull(s);
		if (s == null)
			return null;
		DateTime dt = DateTime.parse(s);
		return new SimpleDate(new Timestamp(dt.getMillis()));
	}

	// public static String noAccents(String string) {
	// return Normalizer.normalize(string,
	// Normalizer.Form.NFKC).replaceAll("[àáâãäåāąă]",
	// "a").replaceAll("[çćčĉċ]", "c").replaceAll("[ďđð]",
	// "d").replaceAll("[èéêëēęěĕė]", "e").replaceAll("[ƒſ]",
	// "f").replaceAll("[ĝğġģ]", "g").replaceAll("[ĥħ]",
	// "h").replaceAll("[ìíîïīĩĭįı]", "i").replaceAll("[ĳĵ]",
	// "j").replaceAll("[ķĸ]", "k").replaceAll("[łľĺļŀ]",
	// "l").replaceAll("[ñńňņŉŋ]", "n").replaceAll("[òóôõöøōőŏœ]",
	// "o").replaceAll("[Þþ]", "p").replaceAll("[ŕřŗ]",
	// "r").replaceAll("[śšşŝș]", "s").replaceAll("[ťţŧț]",
	// "t").replaceAll("[ùúûüūůűŭũų]", "u").replaceAll("[ŵ]",
	// "w").replaceAll("[ýÿŷ]", "y").replaceAll("[žżź]", "z").replaceAll("[æ]",
	// "ae").replaceAll("[ÀÁÂÃÄÅĀĄĂ]", "A").replaceAll("[ÇĆČĈĊ]",
	// "C").replaceAll("[ĎĐÐ]", "D").replaceAll("[ÈÉÊËĒĘĚĔĖ]",
	// "E").replaceAll("[ĜĞĠĢ]", "G").replaceAll("[ĤĦ]",
	// "H").replaceAll("[ÌÍÎÏĪĨĬĮİ]", "I").replaceAll("[Ĵ]",
	// "J").replaceAll("[Ķ]", "K").replaceAll("[ŁĽĹĻĿ]",
	// "L").replaceAll("[ÑŃŇŅŊ]", "N").replaceAll("[ÒÓÔÕÖØŌŐŎ]",
	// "O").replaceAll("[ŔŘŖ]", "R").replaceAll("[ŚŠŞŜȘ]",
	// "S").replaceAll("[ÙÚÛÜŪŮŰŬŨŲ]", "U").replaceAll("[Ŵ]",
	// "W").replaceAll("[ÝŶŸ]", "Y").replaceAll("[ŹŽŻ]", "Z").replaceAll("[ß]",
	// "ss");
	// }

	public static String slugify(String string) {
		return slugify(string, Boolean.TRUE);
	}

	public static String slugify(String string, Boolean lowercase) {
		// string = noAccents(string);
		// Apostrophes.
		string = string.replaceAll("([a-z])'s([^a-z])", "$1s$2");
		string = string.replaceAll("[^\\w]", "-").replaceAll("-{2,}", "-");
		// Get rid of any - at the start and end.
		string = string.replaceAll("-+$", "").replaceAll("^-+", "");

		return (lowercase ? string.toLowerCase() : string);
	}

}

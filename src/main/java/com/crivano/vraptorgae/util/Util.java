package com.crivano.vraptorgae.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.joda.time.LocalDate;

public class Util {
	public final static Charset UTF8 = Charset.forName("UTF-8");
	public final static Charset ISO88591 = Charset.forName("ISO-8859-1");
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static String stringOrNull(String s) {
		if (s == null)
			return null;
		if (s.trim().length() == 0)
			return null;
		return s;
	}

	public static String mySlugify(String string, Boolean lowercase) {
		string = JavaExtensions.noAccents(string);
		// Apostrophes.
		string = string.replaceAll("([a-z])'s([^a-z])", "$1s$2");
		string = string.replaceAll("[ ]", "-").replaceAll("-{2,}", "-");
		// Get rid of any - at the start and end.
		string.replaceAll("-+$", "").replaceAll("^-+", "");

		return (lowercase ? string.toLowerCase() : string);
	}

	public static long copyLarge(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static LocalDate localDateFromMonthString(String month) {
		return new LocalDate(Integer.valueOf(month.substring(0, 4)),
				Integer.valueOf(month.substring(5, 7)), 1);
	}

	public static LocalDate localDateFromDayString(String day) {
		return new LocalDate(Integer.valueOf(day.substring(0, 4)),
				Integer.valueOf(day.substring(5, 7)), Integer.valueOf(day
						.substring(8, 10)));
	}

	public static LocalDate localDateFromString(String day) {
		return new LocalDate(
				Integer.valueOf(day.substring(day.lastIndexOf("/") + 1)),
				Integer.valueOf(day.substring(day.indexOf("/") + 1,
						day.lastIndexOf("/"))), Integer.valueOf(day.substring(
						0, day.indexOf("/"))));
	}

	public static byte[] compress(byte[] str) throws Exception {
		if (str == null || str.length == 0) {
			return null;
		}
		System.out.println("String length : " + str.length);
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(obj);
		gzip.write(str);
		gzip.close();
		return obj.toByteArray();
	}

	public static byte[] decompress(byte[] str) throws Exception {
		int BUFFER = 8192;
		byte data[] = new byte[BUFFER];
		int count;
		byte out[];

		if (str == null || str.length == 0) {
			return null;
		}
		System.out.println("Input byte[] length : " + str.length);
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str));

		// write the files to the disk
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		while ((count = gis.read(data, 0, BUFFER)) != -1) {
			fos.write(data, 0, count);
		}
		fos.flush();
		fos.close();
		out = fos.toByteArray();

		System.out.println("Output String lenght : " + out.length);
		return out;
	}

}

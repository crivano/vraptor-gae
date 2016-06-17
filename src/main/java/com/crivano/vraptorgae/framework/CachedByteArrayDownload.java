package com.crivano.vraptorgae.framework;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

public class CachedByteArrayDownload implements Download {

	private final InputStreamDownload download;
	private final HttpServletRequest request;
	private final String etag;

	public CachedByteArrayDownload(HttpServletRequest request, byte[] buff,
			String contentType, String fileName) {
		this(request, buff, contentType, fileName, false);
	}

	public CachedByteArrayDownload(HttpServletRequest request, byte[] buff,
			String contentType, String fileName, boolean doDownload) {
		this.request= request; 
		this.etag = Integer.toHexString(java.util.Arrays.hashCode(buff));
		ByteArrayInputStream stream = new ByteArrayInputStream(buff);
		this.download = new InputStreamDownload(stream, contentType, fileName,
				doDownload, buff.length);
	}

	public void write(HttpServletResponse response) throws IOException {
		String ifNoneMatch = this.request.getHeader("If-None-Match");
		response.setHeader("ETag", etag);
		if (ifNoneMatch != null && etag.equals(ifNoneMatch)) {
			response.setStatus(304);
			return;
		}
		response.setHeader("Cache-Control",
				"public, must-revalidate");
		//response.setDateHeader("Last-Modified", new Date().getTime());
		download.write(response);
	}
}
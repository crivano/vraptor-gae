package com.crivano.vraptorgae.util;

public class ErrorMessage {
	private String error;

	public ErrorMessage(Throwable t) {
		this.error = t.getMessage();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}

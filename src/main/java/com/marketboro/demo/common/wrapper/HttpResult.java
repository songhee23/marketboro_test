package com.marketboro.demo.common.wrapper;

import lombok.Getter;

@Getter
public class HttpResult {
	private final int statusCode;
	private final String message;

	public HttpResult(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}

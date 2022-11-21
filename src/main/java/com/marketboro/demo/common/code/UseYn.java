package com.marketboro.demo.common.code;

import com.marketboro.demo.common.code.converter.CodeValue;

public enum UseYn implements CodeValue {
	Y("y"),
	N("n");

	private String code;

	UseYn(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

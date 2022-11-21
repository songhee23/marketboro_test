package com.marketboro.demo.common.code;

import com.marketboro.demo.common.code.converter.CodeValue;

public enum PointStatus implements CodeValue {
	USE("use"),
	ADD("add");

	private String code;

	PointStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

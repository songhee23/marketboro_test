package com.marketboro.demo.common.code.converter;

import com.marketboro.demo.common.code.UseYn;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UseYnConverter implements AttributeConverter<UseYn, String> {

	@Override
	public String convertToDatabaseColumn(UseYn attribute) {
		return attribute.getCode();
	}

	@Override
	public UseYn convertToEntityAttribute(String dbData) {
		return EnumSet.allOf(UseYn.class).stream()
			.filter(e->e.getCode().equals(dbData))
			.findAny()
			.orElseThrow(()-> new NoSuchElementException());
	}
}
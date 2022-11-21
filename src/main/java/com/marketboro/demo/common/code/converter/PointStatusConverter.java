package com.marketboro.demo.common.code.converter;

import com.marketboro.demo.common.code.PointStatus;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointStatusConverter implements AttributeConverter<PointStatus, String> {

	@Override
	public String convertToDatabaseColumn(PointStatus attribute) {
		return attribute.getCode();
	}

	@Override
	public PointStatus convertToEntityAttribute(String dbData) {
		return EnumSet.allOf(PointStatus.class).stream()
			.filter(e->e.getCode().equals(dbData))
			.findAny()
			.orElseThrow(()-> new NoSuchElementException());
	}
}
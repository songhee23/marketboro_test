package com.marketboro.demo.domain;

import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.common.code.converter.UseYnConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "user")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", length = 11, nullable = false)
	private Integer userId;

	// 사용자 포인트 ID
	@Column(name = "userPointId", length = 10)
	private Integer userPointId;

	// 계정 아이디
	@Column(name = "account", length = 45)
	private String account;

	// 사용자 사용 여부
	@Column(name = "useYn", length = 1)
	@Convert(converter = UseYnConverter.class)
	private UseYn useYn;

}
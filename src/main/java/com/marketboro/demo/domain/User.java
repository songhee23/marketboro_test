package com.marketboro.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.common.code.converter.UseYnConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", length = 11, nullable = false)
	private Integer userId;

	// 계정 아이디
	@Column(name = "account", length = 45)
	private String account;

	// 최종 포인트
	@Column(name = "point", length = 10)
	private Integer point;

	// 사용자 사용 여부
	@Column(name = "useYn", length = 1)
	@Convert(converter = UseYnConverter.class)
	private UseYn useYn;

	@CreatedDate
	@Column(name="regDateTime", columnDefinition = "TIMESTAMP", updatable = false)
	private Timestamp regDateTime;

	@LastModifiedDate
	@Column(name="modDateTime", columnDefinition = "TIMESTAMP")
	private Timestamp modDateTime;

}
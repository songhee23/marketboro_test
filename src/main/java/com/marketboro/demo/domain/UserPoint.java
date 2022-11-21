package com.marketboro.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marketboro.demo.common.code.PointStatus;
import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.common.code.converter.PointStatusConverter;
import com.marketboro.demo.common.code.converter.UseYnConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.Manager;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "userpoint")
public class UserPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userPointId", length = 11, nullable = false)
	private Integer userPointId;

	// 연결된 사용자 ID(User PK)
	@Column(name = "userId", length = 10)
	private Integer userId;

	// 포인트
	@Column(name = "point", length = 10)
	private Integer point;

	// 포인트 상태(사용-, 추가+)
	@Column(name = "pointStatus", length = 1)
	@Convert(converter = PointStatusConverter.class)
	private PointStatus pointStatus;

	@CreatedDate
	@Column(name="regDateTime", columnDefinition = "TIMESTAMP", updatable = false)
	private Timestamp regDateTime;

	@LastModifiedDate
	@Column(name="modDateTime", columnDefinition = "TIMESTAMP")
	private Timestamp modDateTime;
}

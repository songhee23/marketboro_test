package com.marketboro.demo.dto;

import com.marketboro.demo.common.code.PointStatus;
import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.domain.UserPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class UserPointDto {

	private Integer userPointId;
	private Integer userId;
	private Integer point;
	private PointStatus pointStatus;
	private Timestamp regDateTime;
	private Timestamp modDateTime;

	public UserPointDto(UserPoint userPoint) {
		this.userPointId = userPoint.getUserPointId();
		this.userId = userPoint.getUserId();
		this.point = userPoint.getPoint();
		this.pointStatus = userPoint.getPointStatus();
		this.regDateTime = userPoint.getRegDateTime();
		this.modDateTime = userPoint.getModDateTime();
	}

	public UserPoint toEntity() {
		return UserPoint.builder()
			.userPointId(userPointId)
			.userId(userId)
			.point(point)
			.pointStatus(pointStatus)
			.regDateTime(regDateTime)
			.modDateTime(modDateTime)
			.build();
	}
}

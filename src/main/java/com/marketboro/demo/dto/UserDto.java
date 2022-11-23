package com.marketboro.demo.dto;

import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.domain.User;
import com.marketboro.demo.domain.UserPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

	private Integer userId;
	private Integer userPointId;
	private String account;
	private Integer point;
	private UseYn useYn;
	private Timestamp regDateTime;
	private Timestamp modDateTime;

	public UserDto(User user) {
		this.userId = user.getUserId();
		this.userPointId = getUserPointId();
		this.account = user.getAccount();
		this.useYn = user.getUseYn();
		this.regDateTime = user.getRegDateTime();
		this.modDateTime = user.getModDateTime();
	}

	public User toEntity() {
		return User.builder()
			.userId(userId)
			.account(account)
			.userPointId(userPointId)
			.useYn(useYn)
			.regDateTime(regDateTime)
			.modDateTime(modDateTime)
			.build();
	}
}

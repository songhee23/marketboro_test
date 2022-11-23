package com.marketboro.demo.service;

import com.marketboro.demo.common.code.ErrorCode;
import com.marketboro.demo.common.code.PointStatus;
import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.common.error.exception.BusinessException;
import com.marketboro.demo.domain.User;
import com.marketboro.demo.domain.UserPoint;
import com.marketboro.demo.dto.UserDto;
import com.marketboro.demo.dto.UserPointDto;
import com.marketboro.demo.repository.UserPointRepository;
import com.marketboro.demo.repository.UserRepository;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserPointService {

	@Autowired
	UserPointRepository userPointRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	public UserPointDto setManagerPointStatus(Integer userId, Integer point, PointStatus pointStatus) throws Exception {
		User user = userRepository.findByUserIdAndUseYn(userId, UseYn.Y);
		Integer currentPoint = 0;
		if (user != null && user.getUserPointId() != 0) {
			UserPoint currentUserPoint = userPointRepository.findByUserPointId(user.getUserPointId());
			currentPoint = currentUserPoint.getCurrentPoint();
		}

		UserPoint userPointBuilder = UserPoint.builder()
			.userId(userId)
			.currentPoint(pointStatus.equals(PointStatus.ADD) ? currentPoint + point : currentPoint - point)
			.pointStatus(pointStatus)
			.point(point)
			.build();
		// 사용자 포인트 등록
		UserPoint insertUserPoint = userPointRepository.save(userPointBuilder);

		// 사용자 > 유저 포인트 이력 마지막 아이디 업데이트
		UserDto userDto = new UserDto(user);
		userDto.setUserPointId(insertUserPoint.getUserPointId());
		userRepository.save(userDto.toEntity());

		return new UserPointDto(insertUserPoint);
	}

	@Transactional(readOnly = true)
	public UserPointDto getUserPointInfo(Integer userId) {
		User user = userRepository.findByUserIdAndUseYn(userId, UseYn.Y);
		if (user == null) {
			throw new BusinessException(ErrorCode.USER_NOT_FOUND);
		}
		UserPoint currentUserPoint = userPointRepository.findByUserPointId(user.getUserPointId());
		return new UserPointDto(currentUserPoint);
	}

	@Transactional(readOnly = true)
	public Page<UserPointDto> searchUserPoint(Integer userId, int size, int page) {
		User user = userRepository.findByUserIdAndUseYn(userId, UseYn.Y);
		if (user == null) {
			throw new BusinessException(ErrorCode.USER_NOT_FOUND);
		}
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<UserPoint> userPoints = userPointRepository.findUserPointsByUserIdOrderByRegDateTimeDesc(userId, pageRequest);
		if (userPoints.getTotalPages() == 0) {
			throw new BusinessException(ErrorCode.NO_SEARCHED_USER_POINT);
		}
		return userPoints.map(UserPointDto::new);
	}
}

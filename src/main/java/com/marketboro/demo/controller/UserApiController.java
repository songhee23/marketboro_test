package com.marketboro.demo.controller;

import com.marketboro.demo.common.code.PointStatus;
import com.marketboro.demo.common.wrapper.Result;
import com.marketboro.demo.dto.UserPointDto;
import com.marketboro.demo.service.UserPointService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserApiController {

	@Autowired
	UserPointService userPointService;

	/**
	 * 회원별 적립금 합계 조회
	 * @param userId 회원 아이디
	 */
	@PostMapping("/sum/point")
	@Operation(summary = "회원별 적립금 합계 조회", description = "회원별 적립금 합계 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "회원 아이디 ", required = true),
	})
	public Result<UserPointDto> sumPoint(@RequestParam("userId") Integer userId) {
		UserPointDto userPointDto = userPointService.getUserPointInfo(userId);
		return Result.<UserPointDto>builder()
			.result(userPointDto)
			.build();
	}

	/**
	 * 회원별 적립금 적립/사용 내역 조회(페이징처리)
	 * @param userId 회원 아이디
	 */
	@PostMapping("/point-history/list")
	@Operation(summary = "회원별 적립금 적립/사용 내역 조회", description = "회원별 적립금 적립/사용 내역 조회(페이징처리)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "회원 아이디 ", required = true),
	})
	public Result<Page<UserPointDto>> pointList(
		@RequestParam("userId") Integer userId,
		@RequestParam("page") int page,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		Page<UserPointDto> userPointDtoList = userPointService.searchUserPoint(userId, size, page);
		return Result.<Page<UserPointDto>>builder()
			.result(userPointDtoList)
			.build();
	}

	/**
	 * 회원별 적립금 적립
	 * @param userId 회원 아이디
	 */
	@PostMapping("/add/point")
	@Operation(summary = "회원별 적립금 적립", description = "회원별 적립금 적립")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "회원 아이디 ", required = true),
		@ApiImplicitParam(name = "point", value = "적립 포인트", required = true),
	})
	public Result<UserPointDto> addPoint(
		@RequestParam("userId") Integer userId, @RequestParam("point") Integer point) throws Exception {
		UserPointDto userPointDto = userPointService.setManagerPointStatus(userId, point, PointStatus.ADD);
		return Result.<UserPointDto>builder()
			.result(userPointDto)
			.build();
	}

	/**
	 * 회원별 적립금 사용
	 * @param userId 회원 아이디
	 */
	@PostMapping("/use/point")
	@Operation(summary = "회원별 적립금 사용", description = "회원별 적립금 사용")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "회원 아이디", required = true),
		@ApiImplicitParam(name = "point", value = "사용 포인트", required = true),
	})
	public Result<UserPointDto> usePoint(
		@RequestParam("userId") Integer userId, @RequestParam("point") Integer point) throws Exception {
		UserPointDto userPointDto = userPointService.setManagerPointStatus(userId, point, PointStatus.USE);
		return Result.<UserPointDto>builder()
			.result(userPointDto)
			.build();
	}
}

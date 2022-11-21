package com.marketboro.demo.common.error;

import com.marketboro.demo.common.code.ErrorCode;
import com.marketboro.demo.common.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다. HttpMessageConverter 에서 등록한
	 * HttpMessageConverter binding 못할경우 발생 주로 @RequestBody, @RequestPart 어노테이션에서 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected void handleMethodArgumentNotValidException(
		MethodArgumentNotValidException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,
			err.getBindingResult());
		log.error("handleMethodArgumentNotValidException", err);
	}

	/**
	 * @ModelAttribute 으로 binding error 발생시 BindException 발생한다. ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
	 * @param err
	 * @return
	 */
	@ExceptionHandler(org.springframework.validation.BindException.class)
	protected void handleBindException(BindException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,
			err.getBindingResult());
		log.error("handleBindException", err);
	}

	/**
	 * enum type 일치하지 않아 binding 못할 경우 발생 주로 @RequestParam enum으로 binding 못했을 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected void handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE);
		log.error("handleMethodArgumentTypeMismatchException", err);
	}

	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected void handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
		log.error("handleHttpRequestMethodNotSupportedException", err);
	}

	/**
	 * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	protected void handleAccessDeniedException(AccessDeniedException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
		log.error("handleAccessDeniedException", err);
	}

	/**
	 * 비즈니스 실행 중 커스텀 에러 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	protected void handleBusinessException(final BusinessException err) {
		final ErrorCode errorCode = err.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		log.error("BusinessException", err);
	}

	/**
	 * Exception 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected void handleException(Exception err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		log.error("handleEntityNotFoundException", err);
	}
}

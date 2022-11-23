package com.marketboro.demo.common.error;

import com.marketboro.demo.common.code.ErrorCode;
import com.marketboro.demo.common.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
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
	protected ErrorResponse handleMethodArgumentNotValidException(
		MethodArgumentNotValidException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,
			err.getBindingResult());
		log.error("handleMethodArgumentNotValidException", err);
		return response;
	}

	/**
	 * @ModelAttribute 으로 binding error 발생시 BindException 발생한다. ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
	 * @param err
	 * @return
	 */
	@ExceptionHandler(org.springframework.validation.BindException.class)
	protected ErrorResponse handleBindException(BindException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,
			err.getBindingResult());
		log.error("handleBindException", err);
		return response;
	}

	/**
	 * enum type 일치하지 않아 binding 못할 경우 발생 주로 @RequestParam enum으로 binding 못했을 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ErrorResponse handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE);
		log.error("handleMethodArgumentTypeMismatchException", err);
		return response;
	}

	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ErrorResponse handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
		log.error("handleHttpRequestMethodNotSupportedException", err);
		return response;
	}

	/**
	 * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	protected ErrorResponse handleAccessDeniedException(AccessDeniedException err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
		log.error("handleAccessDeniedException", err);
		return response;
	}

	/**
	 * 비즈니스 실행 중 커스텀 에러 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	protected ErrorResponse handleBusinessException(final BusinessException err) {
		final ErrorCode errorCode = err.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		log.error("BusinessException", err);
		return response;
	}

	/**
	 * Exception 발생
	 * @param err
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ErrorResponse handleException(Exception err) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		log.error("handleEntityNotFoundException", err);
		return response;
	}
}

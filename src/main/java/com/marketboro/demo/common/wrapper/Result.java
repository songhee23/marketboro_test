package com.marketboro.demo.common.wrapper;

import com.marketboro.demo.common.code.UseYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	@Builder.Default
	private HttpResult httpResult = new HttpResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());

	@Builder.Default
	private String isSuccess = UseYn.Y.getCode();

	@Builder.Default
	private T result = null;
}

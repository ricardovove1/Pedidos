package com.ma.pedidos.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ma.pedidos.dto.error.Error;
import com.ma.pedidos.dto.error.ErrorResponse;

@ControllerAdvice
@ResponseBody
public class RestResponseEntityExceptionHandler {

	/**
	 * Returns type errors in a standard format {@link MethodArgumentNotValidException}
	 * @param ex {@link MethodArgumentNotValidException}
	 * @return {@link ErrorResponse}
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ErrorResponse handleValid(MethodArgumentNotValidException ex) {

		List<Error> errors = ex.getFieldErrors().stream().map(f -> new Error(f.getDefaultMessage()))
				.collect(Collectors.toList());

		return new ErrorResponse(errors);
	}

	/**
	 * Returns the different service errors in a standard format
	 * @param ex {@link ServiceException}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(value = { ServiceException.class })
	protected ResponseEntity<ErrorResponse> handleServiceEception(ServiceException ex) {

		Error error = new Error(ex.getMessage());

		return new ResponseEntity<>(new ErrorResponse(List.of(error)), ex.getCode());
	}

}

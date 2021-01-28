package com.ma.pedidos.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private final HttpStatus code;

	public ServiceException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

}

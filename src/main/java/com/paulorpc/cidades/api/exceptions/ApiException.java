package com.paulorpc.cidades.api.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ApiException {

	private final String date;
	private final int httpStatus;
	private final String error;
	private final String message;
	private final String path;
	
	public ApiException(HttpStatus httpStatus, Exception message, String path) {
		super();
		this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:mm"));
		this.httpStatus = httpStatus.value();
		this.error = httpStatus.name();
		this.message = message.getMessage();
		this.path = path;
	}

	public String getDate() {
		return date;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public String getError() {
		return error;
	}
	public String getMessage() {
		return message;
	}
	public String getPath() {
		return path;
	}
	
	

}

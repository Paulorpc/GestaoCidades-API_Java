package com.paulorpc.cidades.api.exceptions;

public class ApiDefaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiDefaultException(String mensagem) {
		super(mensagem);
	}
	
	public ApiDefaultException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}

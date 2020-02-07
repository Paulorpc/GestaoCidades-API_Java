package com.paulorpc.cidades.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value ={ApiDefaultException.class, Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(Throwable e, WebRequest request) {
		ApiException apiResponse = new ApiException(HttpStatus.BAD_REQUEST,(Exception) e,printUri(request.getDescription(true)) );
		e.printStackTrace();
        return ResponseEntity.badRequest().body(apiResponse);
    }
	
	
	/***
	 * Retorna apenas a parte do URI que interessa ao usuário
	 * @param uriCompleto
	 * @return String URI Util
	 */
	private String printUri(String uriCompleto) {
		return uriCompleto.substring(uriCompleto.indexOf("=")+1, uriCompleto.indexOf(";"));
	}

}

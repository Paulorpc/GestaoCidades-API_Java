package com.paulorpc.cidades.api.reponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private String data;
	private T dados;
	private List<String> erros;
	
	
	public Response() {		
		this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:mm"));
	}
	
	
	public String getData() {
		return data;
	}
	public T getDados() {
		return dados;
	}
	public void setDados(T dados) {
		this.dados = dados;
	}
	public List<String> getErros() {
		if(this.erros == null)
			this.erros = new ArrayList<String>();
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}

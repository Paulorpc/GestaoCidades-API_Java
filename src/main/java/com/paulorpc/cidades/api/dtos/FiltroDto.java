package com.paulorpc.cidades.api.dtos;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

public class FiltroDto {
	
	private String coluna;
	private String valor;
	
	public FiltroDto() {}
	
	
	@NotEmpty(message="Campo 'coluna' não pode ser vazio.")
	@NotNull(message="Campo 'coluna' não pode ser nulo.")
	@Length(min=2, max = 50, message = "Campo 'coluna' de deve conter entre {min} e {max} caracteres.")
	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

		
	@Length(max=50, message="Campo 'valor' de deve conter no máximo {max} caracteres.")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

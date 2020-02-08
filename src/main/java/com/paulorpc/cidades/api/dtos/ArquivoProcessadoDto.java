package com.paulorpc.cidades.api.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoProcessadoDto extends ArquivoDto {
	
	private int totalCadastros;
	private List<CidadeDto> cidades;	
	
    public ArquivoProcessadoDto(MultipartFile arquivo, String nome, String uri, List<CidadeDto> cidades) {
		super(arquivo, nome, uri);
		this.cidades = cidades;
		this.totalCadastros = (cidades == null) ? 0 : cidades.size();
	}
    
	
	public int getTotalCadastros() {
		return totalCadastros;
	}
	public void setTotalCadastros(int totalCadastros) {
		this.totalCadastros = totalCadastros;
	}

	public List<CidadeDto> getCidades() {
		return (cidades == null) ? new ArrayList<>() : this.cidades;
	}
	public void setCidades(List<CidadeDto> cidades) {
		this.cidades = cidades;
	}

}

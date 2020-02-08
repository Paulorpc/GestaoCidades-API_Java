package com.paulorpc.cidades.api.dtos;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoDto {
	
	private MultipartFile arquivo;
	private Optional<String> nome = Optional.empty();
	private Optional<String> key = Optional.empty();
    private Optional<String> uri = Optional.empty();
    private Optional<String> tipo = Optional.empty();
    private Optional<String> tamanho = Optional.empty();
    
    
	public ArquivoDto(MultipartFile arquivo, String nome, String uri) {
		this.arquivo = arquivo;
		this.nome = Optional.ofNullable(nome);
		this.key = Optional.ofNullable(arquivo.getName());
		this.uri = Optional.ofNullable(uri);
		this.tamanho = Optional.ofNullable(arquivo.getSize()/1024 + " KB");
		this.tipo = Optional.ofNullable(arquivo.getContentType());
	}
	
    
	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

	public Optional<String> getNome() {
		return nome;
	}
	public void setNome(Optional<String> nome) {
		this.nome = nome;
	}
	
	public Optional<String> getKey() {
		return key;
	}
	public void setKey(Optional<String> key) {
		this.key = key;
	}

	public Optional<String> getUri() {
		return uri;
	}
	public void setUri(Optional<String> uri) {
		this.uri = uri;
	}

	public Optional<String> getTipo() {
		return tipo;
	}
	public void setTipo(Optional<String> tipo) {
		this.tipo = tipo;
	}

	public Optional<String> getTamanho() {
		return tamanho;
	}
	public void setTamanho(Optional<String> tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public String toString() {
		return "ArquivoDto [arquivo=" + arquivo + ", nome=" + nome + ", uri=" + uri + ", tipo=" + tipo + ", tamanho="
				+ tamanho + "]";
	}

}

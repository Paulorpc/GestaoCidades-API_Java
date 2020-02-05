package com.paulorpc.cidades.api.dtos;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

public class CidadeDto {
	
	private int id;
	private int codigoIbge;
	private String uf;
	private String nome;
	private boolean capital;
	private Optional<BigDecimal> longitude = Optional.empty();
	private Optional<BigDecimal> latitude = Optional.empty();
	private Optional<String> nomeSemAcento = Optional.empty();
	private Optional<String> nomeAlternativo = Optional.empty();
	private Optional<String> microRegiao = Optional.empty();
	private Optional<String> mesoRegiao = Optional.empty();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@NotNull	
	//@Range(min=, max=)
	public int getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	
	@NotNull()
	@Length(min = 2, max = 2, message = "Campo deve conter 2 caracteres")
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@NotEmpty()
	@Length(max = 50, message = "Campo deve conter no máximo 50 caracteres")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull()
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	
	public Optional<@Digits(integer = 10, fraction = 8, message="valor numérico fora dos limites. Esperado: Decimal 11,8.") BigDecimal> getLongitude() {
		return longitude;
	}
	public void setLongitude(Optional<BigDecimal> longitude) {
		this.longitude = longitude;
	}
		
	
	public Optional<@Digits(integer = 10, fraction = 8, message="valor numérico fora dos limites. Esperado: Decimal 10,8.") BigDecimal> getLatitude() {
		return latitude;
	}
	public void setLatitude(Optional<BigDecimal> latitude) {
		this.latitude = latitude;
	}
	
	//@Pattern(regex=, flags=)  	
	public Optional<@Length(max = 50, message = "Campo de deve conter no máximo 50 caracteres") String> getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(Optional<String> nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	
	
	public Optional<@Length(max = 50, message = "Campo de deve conter no máximo 50 caracteres") String> getNomeAlternativo() {
		return nomeAlternativo;
	}
	public void setNomeAlternativo(Optional<String> nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}
	
	
	public Optional<@Length(max = 50, message = "Campo de deve conter no máximo 50 caracteres") String> getMicroregiao() {
		return microRegiao;
	}	
	public void setMicroregiao(Optional<String> microRegiao) {
		this.microRegiao = microRegiao;
	}	

	
	public Optional<@Length(max = 50, message = "Campo de deve conter no máximo 50 caracteres") String> getMesoregiao() {
		return mesoRegiao;
	}
	public void setMesoregiao(Optional<String> mesoRegiao) {
		this.mesoRegiao = mesoRegiao;
	}
	
	@Override
	public String toString() {
		return "CidadeDto [id=" + id + ", codigoIbge=" + codigoIbge + ", uf=" + uf + ", nome=" + nome + ", capital="
				+ capital + ", longitude=" + longitude + ", latitude=" + latitude + ", nomeSemAcento=" + nomeSemAcento
				+ ", nomeAlternativo=" + nomeAlternativo + ", microRegiao=" + microRegiao + ", mesoRegiao=" + mesoRegiao
				+ "]";
	}	

}


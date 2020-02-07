package com.paulorpc.cidades.api.dtos;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
	
	
	@NotNull(message="Campo 'codigoIbge' não pode ser nulo.")	
	@Range(min=1000000, max=9999999, message="Range do campo restrido entre {min} e {max}.")
	public int getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	
	@NotEmpty(message="Campo 'uf' não pode ser vazio.")
	@NotNull(message="Campo 'uf' não pode ser nulo.")
	@Length(min=2, max=2, message="Campo 'uf' deve conter entre {min} e {max} caracteres")
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@NotEmpty(message="Campo 'nome' não pode ser vazio.")
	@NotNull(message="Campo 'nome' não pode ser nulo.")
	@Length(max=50, message="Campo 'nome' deve conter no máximo {max} caracteres")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message="Campo 'capital' deve ser 'true' ou 'false'.")
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	
	public Optional<@Digits(integer = 10, fraction = 8, message="Valor numérico '${validatedValue}' fora dos limites. Esperado: Decimal {integer},{fraction}.") BigDecimal> getLongitude() {
		return longitude;
	}
	public void setLongitude(Optional<BigDecimal> longitude) {
		this.longitude = longitude;
	}
		
	
	public Optional<@Digits(integer = 10, fraction = 8, message="valor numérico '${validatedValue}' fora dos limites. Esperado: Decimal {integer},{fraction}.") BigDecimal> getLatitude() {
		return latitude;
	}
	public void setLatitude(Optional<BigDecimal> latitude) {
		this.latitude = latitude;
	}
	
	
	public Optional<@Length(max = 50, message = "Campo 'nomeSemAcento' de deve conter no máximo {max} caracteres") String> getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(Optional<String> nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	
	
	public Optional<@Length(max = 50, message = "Campo 'nomeAlternadivo' deve conter no máximo {max} caracteres") String> getNomeAlternativo() {
		return nomeAlternativo;
	}
	public void setNomeAlternativo(Optional<String> nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}
	
	
	public Optional<@Length(max = 50, message = "Campo 'microRegiao' deve conter no máximo {max} caracteres") String> getMicroRegiao() {
		return microRegiao;
	}	
	public void setMicroregiao(Optional<String> microRegiao) {
		this.microRegiao = microRegiao;
	}	

	
	public Optional<@Length(max = 50, message = "Campo 'mesoRegiao' deve conter no máximo {max} caracteres") String> getMesoRegiao() {
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


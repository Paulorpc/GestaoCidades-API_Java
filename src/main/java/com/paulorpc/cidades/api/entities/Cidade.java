package com.paulorpc.cidades.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="cidade")
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int ibge_id;
	private String uf;
	private String nome;
	private boolean capital;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String nomeSemAcento;
	private String nomeAlternativo;
	private String microregiao;
	private String mesoregiao;
	private Date dataCriacao;
	private Date dataAlteracao;
	
	public Cidade() {}
	
	
	@PreUpdate
	public void preUpdate() {
		dataAlteracao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		Date hojeHora = new Date();
		dataCriacao = hojeHora;
		dataAlteracao = hojeHora;
	}	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ibge_id", nullable=false)
	public int getIbge_id() {
		return ibge_id;
	}
	public void setIbge_id(int ibge_id) {
		this.ibge_id = ibge_id;
	}
	
	@Column(name="uf", nullable=false)
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Column(name="name", nullable=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="capital", nullable=true)
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	@Column(name="lon", nullable=true)
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="lat", nullable=true)
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="no_accents", nullable=true)
	public String getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	
	@Column(name="alternative_name", nullable=true)
	public String getNomeAlternativo() {
		return nomeAlternativo;
	}
	public void setNomeAlternativo(String nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}
	
	@Column(name="microregion", nullable=true)
	public String getMicroregiao() {
		return microregiao;
	}
	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}
		
	@Column(name="mesoregion", nullable=true)
	public String getMesoregiao() {
		return mesoregiao;
	}
	public void setMesoregiao(String mesoregiao) {
		this.mesoregiao = mesoregiao;
	}
		
	@Column(name="dataCriacao", nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name="dataAlteracao", nullable=false)
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "[Cidade: " +id+", "+ibge_id+", "+uf+", "+nome+", "+capital+", "+", "+longitude+", "+latitude+", "+nomeSemAcento+", "+nomeAlternativo+", "+microregiao+","+mesoregiao+ "]";
	}

}

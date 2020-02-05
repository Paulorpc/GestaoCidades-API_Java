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
	private int codigoIbge;
	private String uf;
	private String nome;
	private boolean capital;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String nomeSemAcento;
	private String nomeAlternativo;
	private String microRegiao;
	private String mesoRegiao;
	private Date dataCriacao;
	private Date dataAlteracao;
	
	public Cidade() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ibge_id", nullable=false)
	public int getcodigoIbge() {
		return codigoIbge;
	}
	public void setcodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
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
		return microRegiao;
	}
	public void setMicroregiao(String microRegiao) {
		this.microRegiao = microRegiao;
	}
		
	
	@Column(name="mesoregion", nullable=true)
	public String getMesoregiao() {
		return mesoRegiao;
	}
	public void setMesoregiao(String mesoRegiao) {
		this.mesoRegiao = mesoRegiao;
	}
	
	
	@Column(name="data_criacao", nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	

	@Column(name="data_alteracao", nullable=false)
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
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

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", codigoIbge=" + codigoIbge + ", uf=" + uf + ", nome=" + nome + ", capital="
				+ capital + ", longitude=" + longitude + ", latitude=" + latitude + ", nomeSemAcento=" + nomeSemAcento
				+ ", nomeAlternativo=" + nomeAlternativo + ", microregiao=" + microRegiao + ", mesoregiao=" + mesoRegiao
				+ ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + "]";
	}	
	
	

}

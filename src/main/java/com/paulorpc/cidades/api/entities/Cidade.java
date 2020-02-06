package com.paulorpc.cidades.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="cidade")
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ibge_id", unique=true,nullable=false)
	private int codigoIbge;
	
	@Column(name="uf", nullable=false)
	private String uf;
	
	@Column(name="name", nullable=false)
	private String nome;
	
	@Column(name="capital", nullable=true)
	private boolean capital;
	
	@Column(name="lon", nullable=true)
	private BigDecimal longitude;
	
	@Column(name="lat", nullable=true)
	private BigDecimal latitude;
	
	@Column(name="no_accents", nullable=true)
	private String nomeSemAcento;
	
	@Column(name="alternative_name", nullable=true)
	private String nomeAlternativo;
	
	@Column(name="microregion", nullable=true)	
	private String microRegiao;
	
	@Column(name="mesoregion", nullable=true)	
	private String mesoRegiao;
	
	@Column(name="data_criacao", nullable=false)
	private Date dataCriacao;
	
	@Column(name="data_alteracao", nullable=false)
	private Date dataAlteracao;
	
	
	public Cidade() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getcodigoIbge() {
		return codigoIbge;
	}
	public void setcodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	public String getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	
	public String getNomeAlternativo() {
		return nomeAlternativo;
	}
	public void setNomeAlternativo(String nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}
	
	public String getMicroregiao() {
		return microRegiao;
	}
	public void setMicroregiao(String microRegiao) {
		this.microRegiao = microRegiao;
	}
			
	public String getMesoregiao() {
		return mesoRegiao;
	}
	public void setMesoregiao(String mesoRegiao) {
		this.mesoRegiao = mesoRegiao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	

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

package com.paulorpc.cidades.api.entities.interfaces;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.persistence.Transient;

import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.exceptions.ApiDefaultException;
import com.paulorpc.cidades.api.utils.EntidadeUtil;

public interface CidadeInt {	
	
	/***
	 * Calcula a distância entre a cidade A e B.
	 * 
	 * Latitude1 (rad): 	  φ1 = lat1.toRadians()
	 * Latitude2 (rad): 	  φ2 = lat2.toRadians()
	 * Diferencial Longitude: Δλ = (lon2-lon1).toRadians()
	 * 
	 * RaioTerra (Km): R = new BigDecimal(6371.0)
	 * 
	 * Fórmula:
	 * Ditância = Math.acos( Math.sin(φ1)*Math.sin(φ2) + Math.cos(φ1)*Math.cos(φ2) * Math.cos(Δλ) ) * R;
	 * 
	 * https://www.movable-type.co.uk/scripts/latlong.html
	 * 
	 * @param A Cidade A
	 * @param B Cidade B
	 * @return BigDecimal Distância
	 */
	@Transient
	public static BigDecimal calcularDistancia(Cidade A, Cidade B) {
		// Raio da terra para correção (considerando terra redonda).
		double R = 6371.0; 
		
		// latitudes das cidades A e B
		double latA = Math.toRadians( A.getLatitude().doubleValue());
		double latB = Math.toRadians( B.getLatitude().doubleValue());
		
		// diferencial de longitude
		double dlo = Math.toRadians( A.getLongitude().doubleValue() - B.getLongitude().doubleValue() );
		
		Double distancia = Math.acos( Math.sin(latA) * Math.sin(latB) + Math.cos(latA)*Math.cos(latB) * Math.cos(dlo) ) * R;		
		
		return new BigDecimal(distancia.doubleValue());
	}
	
	
	/***
	 * Calcula a distância entre a cidade A e B.
	 * 
	 * Latitude1 (rad): 	  φ1 = lat1.toRadians()
	 * Latitude2 (rad): 	  φ2 = lat2.toRadians()
	 * Diferencial Longitude: Δλ = (lon2-lon1).toRadians()
	 * 
	 * RaioTerra (Km): R = new BigDecimal(6371.0)
	 * 
	 * Fórmula:
	 * Ditância = Math.acos( Math.sin(φ1)*Math.sin(φ2) + Math.cos(φ1)*Math.cos(φ2) * Math.cos(Δλ) ) * R;
	 * 
	 * https://www.movable-type.co.uk/scripts/latlong.html
	 * 
	 * @param A Cidade A
	 * @param B Cidade B
	 * @return BigDecimal Distância
	 */
	@Transient
	public BigDecimal calcularDistancia(Cidade B);
	

	/***
	 * Método auxiliar para mapear campos da tabela com atributos da classe 
	 * @param coluna
	 * @return String
	 */
	@Transient
	public static String mapaColunas(String coluna) {

		if(!EntidadeUtil.isColunaEntidadeValida(Cidade.class, coluna))
			throw new ApiDefaultException("Não foi possível identificar a coluna informada.");
		
		HashMap<String, String> mapa = new HashMap<>();
		mapa.put("codigoIbge", "ibge_id");
		mapa.put("uf", "uf");
		mapa.put("nome", "name");
		mapa.put("capital", "capital");
		mapa.put("longitude", "lon");
		mapa.put("latitude", "lat");
		mapa.put("nomeSemAcento", "no_accents");
		mapa.put("nomeAlternativo", "alternative_name");
		mapa.put("microRegiao", "microregion");
		mapa.put("mesoRegiao", "mesoregion");
		
		return mapa.get(coluna);
	}
		
}

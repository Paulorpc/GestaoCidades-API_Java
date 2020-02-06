package com.paulorpc.cidades.api.utils;

import java.util.HashMap;

import javax.persistence.Column;

import com.paulorpc.cidades.api.entities.Cidade;

public class EntidadeUtil {
		
	/***
	 * Verifica se o campo com anotação @Column foi declarado pela classe 
	 * @param Classe.class
	 * @param nome
	 * @return boolean
	 */
	public static boolean isColunaEntidadeValida(Class<?> classe, String nome) {
		try {					
			return classe.getDeclaredField(nome).isAnnotationPresent(Column.class);
		}
		catch(NoSuchFieldException e) {
			return false; 
		}
	}
	
	public static String mapaColunasCidade(String coluna) {

		if(!EntidadeUtil.isColunaEntidadeValida(Cidade.class, coluna))
			return new String();
		
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

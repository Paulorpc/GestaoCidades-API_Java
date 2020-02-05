package com.paulorpc.cidades.api.services;

import java.util.List;
import java.util.Optional;

import com.paulorpc.cidades.api.entities.Cidade;

public interface CidadeService {
	
	
	/***
	 * Busca uma cidade pelo id
	 * @param id
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> buscarPorId(int id);
	
	
	/***
	 * Busca uma cidade pelo nome
	 * @param nome
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> buscarPorNome(String nome);
	
	/***
	 * Busca todas as cidades
	 * @return Optional<List<Cidade>>
	 */
	public List<Cidade> buscarTodas();
	
	/***
	 * Busca todas as cidades que são capitais (ordernadas por nome)
	 * Exercício 2
	 * @return Optional<List<Cidade>>
	 */
	public List<Cidade> buscarPorCapitaisOrdenadaPorNome();
	
	/***
	 * Busca uma cidade pelo código ibge
	 * Exercício 5
	 * @param nome
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> buscarPorCodigoIbge(int codigoIbge);
	
	
	/***
	 * Busca todas as cidades que pertencem ao estado solicitado
	 * Exercício 6
	 * @param uf
	 * @return List<Cidade>
	 */
	public List<Cidade> buscarPorUf(String uf);
	
	
	/***
	 * Cadastra uma cidade no banco de dados
	 * Exercício 7
	 * @param cidade
	 * @return Cidade
	 */
	Cidade persistir(Cidade cidade);
	
	/***
	 * Deletar uma cidade pelo id
	 * Exercício 8
	 * @param id
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> deletar(int id);
	
}

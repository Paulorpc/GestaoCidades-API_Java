package com.paulorpc.cidades.api.services;

import java.util.HashMap;
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
	 * Busca toas as cidades
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
	 * Busca os estados com a maior e menor quantidade de cidades e o total de cidades.
	 * Exercício 3
	 * @return List<HashMap<String, Integer>>
	 */	
	public List<HashMap<String, String>> buscarEstadosMaiorMenorQtdeCidades();
	
	/***
	 * Busca a quantidade de cidades por estado
	 * Exercício 4
	 * @param orderBy 'coluna'
	 * @return List<HashMap<String, Integer>>	
	 */
	public List<HashMap<String, String>> buscarQtdePorEstado(String sort);
	
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

	/***
	 * Buscar todas as cidades que contenham um determinado dado na coluna
	 * Exercício 9
	 * @param coluna Campo tabela
	 * @param dado 
	 * @return List<Cidade>
	 */
	List<Cidade> buscarPorFiltroColunaValor(String coluna, String valor);	
	
	/***
	 * Buscar a qtde de registros únicos filtrados por uma determinada coluna
	 * Exercício 10
	 * @param coluna Campo tabela	  
	 * @return List<Cidade>
	 */
	List<Object> buscarRegistrosUnicosPorFiltroColuna(String coluna);
	
	/***
	 * Buscar a qtde total de cidades armazenadas
	 * Exercício 11
	 * @return HashMap<String, Long> 
	 */
	HashMap<String, Long> buscarQtdeRegistros();

}

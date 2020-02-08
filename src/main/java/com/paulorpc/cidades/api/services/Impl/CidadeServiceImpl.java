package com.paulorpc.cidades.api.services.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.entities.interfaces.CidadeInt;
import com.paulorpc.cidades.api.repositories.CidadeRepository;
import com.paulorpc.cidades.api.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Optional<Cidade> buscarPorId(int id) {
		log.info("Buscando cidade por id: {}", id);
		return Optional.ofNullable(cidadeRepository.findById(id));		
	}
	
	@Override
	public Optional<Cidade> buscarPorNome(String nome) {
		log.info("Buscando cidade por nome: {}", nome);
		return Optional.ofNullable(cidadeRepository.findByNome(nome));		
	}
	
	@Override
	public Optional<Cidade> buscarPorCodigoIbge(int codigoIbge) {
		log.info("Buscando cidade por codigo ibge: {}", codigoIbge);
		return Optional.ofNullable(cidadeRepository.findByCodigoIbge(codigoIbge));
	}
	
	public List<Cidade> buscarTodas(){
		log.info("Buscando todas as cidades.");
		return cidadeRepository.findAll();
	}
	
	@Override
	public List<Cidade> buscarPorCapitaisOrdenadaPorNome() {
		log.info("Buscando capitais ordernadas por nome.");
		return cidadeRepository.findAllByCapitalIsTrueOrderByNome();
	}

	@Override
	public Cidade persistir(Cidade cidade) {
		log.info("Persistindo cidade: {}", cidade);
		return cidadeRepository.save(cidade);
	}
	
	@Override
	public List<Cidade> persistirTodas(List<Cidade> cidades) {
		log.info("Persistindo cidade: {}", cidades.toString());
		return cidadeRepository.saveAll(cidades);
	}

	@Override
	public List<HashMap<String, String>> buscarQtdePorEstado(String sort) {
		log.info("Buscando qtde de cidades por estado.");
		Query qy = em.createNativeQuery("SELECT uf, count(name) AS qtde FROM cidade GROUP BY uf ORDER BY " +sort );		
		Object[] rs = qy.getResultList().toArray();
		List<HashMap<String,String>> lista = new ArrayList<>();
				
		for(Object obj : rs) {
			HashMap<String, String> mRs = new HashMap<>();
			Object[] o = (Object[]) obj;
			mRs.put( "uf", String.valueOf(o[0]) );
			mRs.put( "qtde", String.valueOf(o[1]) );
			lista.add(mRs);
		}
		return lista;
	}

	@Override
	public List<HashMap<String, String>> buscarEstadosMaiorMenorQtdeCidades() {
		log.info("Buscando estados com maior/menor qtde cidades.");
		
		List<HashMap<String,String>> lista = new ArrayList<>();
		List<HashMap<String,String>> listaRs = this.buscarQtdePorEstado("qtde");
		
		lista.add(listaRs.get(0));
		lista.add(listaRs.get(listaRs.size()-1));			
		
		return lista;
	}

	@Override
	public List<Cidade> buscarPorUf(String uf) {
		log.info("Buscando cidades de um determinado estado: {}", uf);
		return cidadeRepository.findAllByUf(uf);
	}

	@Override
	public Optional<Cidade> deletar(int id) {
		log.info("Deletando cidade: {}", id);
		Cidade cidade = cidadeRepository.findById(id);

		if (cidade != null)	
			cidadeRepository.deleteById(id);
		
		return Optional.ofNullable(cidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> buscarPorFiltroColunaValor(String coluna, String valor) {
		log.info("Buscando cidades por filtro de coluna/valor.");					
		Query qy = em.createNativeQuery("SELECT * FROM cidade WHERE "+coluna+" = '"+valor+"'", Cidade.class);	
		return qy.getResultList();				
	}

	@Override
	public List<Object> buscarRegistrosUnicosPorFiltroColuna(String coluna) {
		log.info("Buscando registros únicos por filtro de coluna.");				
		Query qy = em.createNativeQuery("SELECT distinct IFNULL("+coluna+", '') as coluna FROM cidade");
		Object[] rs = qy.getResultList().toArray();		
		return Arrays.asList(rs);
	}
	
	@Override
	public HashMap<String,Long> buscarQtdeRegistros() {
		log.info("Buscando qtde total de cidades.");
		HashMap<String,Long> total = new HashMap<>();
		total.put("totalCidades", cidadeRepository.count());
		return total;
	}

	/***
	 * Método para identificar quais as duas cidades mais distantes entre si.
	 * 
	 * Algoritmo com complexidade O(n2) melhorado, pois não percorre indices repetidos.
	 * Provável que seja possível melhorar para O(n) ou O(lg n) com algoritmos
	 * otimizados, como de árvore, por exemplo.  
	 */
	@Override
	public List<Cidade> buscarCidadesMaisDistantes() {
		Cidade A = new Cidade();
		Cidade B = new Cidade();
		List<Cidade> resultado = new ArrayList<>();
		BigDecimal maiorDistancia = new BigDecimal(0.0);

		List<Cidade> cidades = cidadeRepository.findAll();
	
		if(cidades.isEmpty())
			return resultado;
		
		long total = cidades.size();
		
	    for(int i=0; i<total-1; i++) {	    	
	    	A = cidades.get(i);
	    	for(int j=i+1; j<total; j++) { 	    		
	    		B = cidades.get(j);
	    		BigDecimal distanciaAB = CidadeInt.calcularDistancia(A, B);
	    		if (distanciaAB.compareTo(maiorDistancia) > 0) {
		    			maiorDistancia = distanciaAB;
		    			resultado.clear();
		    			resultado.addAll(Arrays.asList(A, B));
	    		}
	    	}
	    }	
		return resultado;
	}

}
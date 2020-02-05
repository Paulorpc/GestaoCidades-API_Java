package com.paulorpc.cidades.api.services.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.repositories.CidadeRepository;
import com.paulorpc.cidades.api.services.CidadeService;

public class CidadeServiceImpl implements CidadeService   {
	
	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Optional<Cidade> buscarPorId(int id) {
		log.info("buscando cidade por id: {}", id);
		return Optional.ofNullable(cidadeRepository.findById(id));		
	}

	@Override
	public Optional<Cidade> buscarPorNome(String nome) {
		log.info("buscando cidade por nome: {}", nome);
		return Optional.ofNullable(cidadeRepository.findByNome(nome));		
	}
	
	public List<Cidade> buscarTodas(){
		log.info("buscando todas as cidades");
		return cidadeRepository.findAll();
	}
	
	@Override
	public List<Cidade> buscarPorCapitaisOrdenadaPorNome() {
		log.info("buscando capitais ordernadas por nome");
		return cidadeRepository.findAllByCapitalIsTrueOrderByNome();
	}

	@Override
	public Optional<Cidade> buscarPorCodigoIbge(int codigoIbge) {
		log.info("buscando cidade por codigo ibge: {}", codigoIbge);
		return Optional.ofNullable(cidadeRepository.findByCodigoIbge(codigoIbge));
	}

	@Override
	public List<Cidade> buscarPorUf(String uf) {
		log.info("buscando cidades de um determinado estado");
		return cidadeRepository.findAllByUf(uf);
	}
	
	@Override
	public Cidade persistir(Cidade cidade) {
		log.info("persistindos cidade: {}", cidade);
		return cidadeRepository.save(cidade);
	}

	@Override
	public Optional<Cidade> deletar(int id) {
		log.info("deletando cidade.");
		Cidade cidade = cidadeRepository.findById(id);

		if (cidade != null)	
			cidadeRepository.deleteById(id);
		
		return Optional.ofNullable(cidade);
	}


}

package com.paulorpc.cidades.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulorpc.cidades.api.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	Cidade findById(int id);
	
	Cidade findByNome(String nome);
	
	Cidade findByNomeAlternativo(String nomeAlternativo);
	
	Cidade findByCodigoIbge(int codigoIbge);
	
	List<Cidade> findAll();
	
	List<Cidade> findAllByUf(String uf);
	
	List<Cidade> findAllByCapitalIsTrueOrderByNome();
	
}

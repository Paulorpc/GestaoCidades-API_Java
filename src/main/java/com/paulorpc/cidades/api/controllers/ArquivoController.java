package com.paulorpc.cidades.api.controllers;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulorpc.cidades.api.dtos.ArquivoDto;
import com.paulorpc.cidades.api.dtos.ArquivoProcessadoDto;
import com.paulorpc.cidades.api.dtos.CidadeDto;
import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.reponse.Response;
import com.paulorpc.cidades.api.services.ArquivoService;
import com.paulorpc.cidades.api.services.CidadeService;
import com.paulorpc.cidades.api.utils.Util;


@RestController
@RequestMapping("/api/arquivos")
@CrossOrigin("*")
public class ArquivoController {
	
	private static final Logger log = LoggerFactory.getLogger(ArquivoController.class);
	
	@Autowired
	private ArquivoService arquivoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	public ArquivoController() {}
	
	
	/***
	 * Permite enviar um arquivo para o servidor.
	 * Obs: Necessário enviar como Key: generico
	 * @param arquivo
	 * @return
	 */
	@PostMapping(value="/upload")
	public ResponseEntity<Response<ArquivoDto>> uploadArquivo(@RequestParam("generico") MultipartFile arquivo) {
		log.info("Enviando arquivo.");
		Response<ArquivoDto> response = new Response<>();	
		String nome = new String();
		String uri = new String();
		
		try {
			Path caminhoArquivo = Util.getCaminhoArquivos().getRepositorioCidades();
			nome = arquivoService.armazenar(arquivo, caminhoArquivo);
			log.info("Arquivo enviado com sucesso: {}", arquivo.getName());
			
			uri = ServletUriComponentsBuilder.fromCurrentContextPath()
											 .path("api/arquivos/upload")				
								             .path(nome)
								             .toUriString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}		
						
		response.setDados(new ArquivoDto(arquivo, nome, uri));
		return ResponseEntity.ok(response);
	}	
	
	
	/***
	 * Permite ler uma arquivo enviado e cadastrar as cidades no banco de dados.
	 * Obs: Necessário enviar como Key: cidades 
	 * @param arquivo MultiPart/form-data
	 */
	@PostMapping(value="/upload/cidades")
	public ResponseEntity<Response<ArquivoProcessadoDto>> uploadArquivoPersisteCidades(@RequestParam("cidades") MultipartFile arquivo) {
		log.info("Enviar arquivo.");
		Response<ArquivoProcessadoDto> response = new Response<>();	
		List<Cidade> cidadesArquivo = new ArrayList<>();
		List<Cidade> cidadesBanco = new ArrayList<>();
		List<Cidade> cidadesPersistidas = new ArrayList<>();
		List<CidadeDto> cidadesDto = new ArrayList<>();
		List<Integer> codigosIbge = new ArrayList<>();
		HashSet<Integer> hCidadesBanco= new HashSet<>();		
		String nome = new String();
		String uri = new String();
		
		try {
			Path caminhoArquivo = Util.getCaminhoArquivos().getRepositorioCidades();
			nome = arquivoService.armazenar(arquivo, caminhoArquivo);
			log.info("Arquivo enviado com sucesso: {}", arquivo.getName());
			
			 uri = ServletUriComponentsBuilder.fromCurrentContextPath()
											  .path("api/arquivos/upload")				
								              .path(nome)
								              .toUriString();
			 
			log.info("Cadastrar cidades do arquivo enviado.");
			cidadesArquivo = Util.gerarCidadesDoArquivoCsv(caminhoArquivo, nome,",");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		cidadesBanco = cidadeService.buscarTodas();
		cidadesBanco.forEach(c->hCidadesBanco.add(c.getcodigoIbge()));
		
		
		for(Cidade c : cidadesArquivo) {			
			if(hCidadesBanco.contains(c.getcodigoIbge()))
				codigosIbge.add(c.getcodigoIbge());				
			else {
				try {
					cidadesPersistidas.add(cidadeService.persistir(c));
				}
				catch(Exception e) {
					response.getErros().add("Erro ao cadastrar cidade. CódigoIbge: " + c.toString());
				}
			}
		}			
		
		if(!codigosIbge.isEmpty())
			response.getErros().add("Impossível cadastrar outra cidade com um mesmo código ibge ou a cidade já já está cadastrada. CódigosIbge: " + codigosIbge.toString());				
				
		cidadesPersistidas.forEach(c -> cidadesDto.add(CidadeController.converterCidadeParaCidadeDto(c)));
		log.info("Cidades enviadas pelo arquivo persistidas com sucesso.");
		
		response.setDados(new ArquivoProcessadoDto(arquivo, nome, uri, cidadesDto));
		return ResponseEntity.ok(response);
	}

}

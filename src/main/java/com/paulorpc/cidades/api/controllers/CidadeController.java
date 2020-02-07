package com.paulorpc.cidades.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulorpc.cidades.api.dtos.CidadeDto;
import com.paulorpc.cidades.api.dtos.FiltroDto;
import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.entities.interfaces.CidadeInt;
import com.paulorpc.cidades.api.exceptions.ApiDefaultException;
import com.paulorpc.cidades.api.reponse.Response;
import com.paulorpc.cidades.api.services.CidadeService;
import com.paulorpc.cidades.api.services.Impl.CidadeServiceImpl;

@RestController
@RequestMapping("/api/cidades")
@CrossOrigin("*") // TODO ALTERAR CROSSORIGIN 
public class CidadeController {
	
	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	@Autowired
	private CidadeService cidadeService;

	
	public CidadeController() {}
	

	/***
	 * Exercício 2
	 */
	@GetMapping(value="/capitais")
	public ResponseEntity<Response<List<CidadeDto>>> buscarCapitaisOrdenadasNome() {
		log.info("Buscar cidades capitais ordenadas por nome.");
		Response<List<CidadeDto>> response = new Response<List<CidadeDto>>();		
		List<CidadeDto> cidadesDto = new ArrayList<>();
					
		List<Cidade> cidades = cidadeService.buscarPorCapitaisOrdenadaPorNome();
		
		if(!cidades.isEmpty())
			cidades.forEach(c -> cidadesDto.add(this.converterCidadeParaCidadeDto(c)));		
		else {
			log.info("Cidades capitais ordernadas por nome não localizadas.");
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}		
			
		response.setDados(cidadesDto);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 3
	 */
	@GetMapping(value="/uf/mais_menos_qtde_cidades")
	public ResponseEntity<Response<List<HashMap<String, String>>>> buscarEstadosMaiorMenorQtdeCidades(){
		log.info("Buscar qtde de cidades para os estados com maior e menor número de cidades");
		Response<List<HashMap<String,String>>> response = new Response<>();
		
		List<HashMap<String, String>> lista = cidadeService.buscarEstadosMaiorMenorQtdeCidades();
		
		if(lista.isEmpty()) {
			log.info("Busca por qtde de cidades para os estados com maior e menor número de cidades não localizado.");
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setDados(lista);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 4 
	 */
	@GetMapping(value="/qtde_por_estado")
	public ResponseEntity<Response<List<HashMap<String, String>>>> buscarQtdeCidadesPorEstado(){
		log.info("Buscar qtde de capitais por estado.");
		Response<List<HashMap<String,String>>> response = new Response<>();
		
		List<HashMap<String, String>> lista = cidadeService.buscarQtdePorEstado("uf");
		
		if(lista.isEmpty()) {
			log.info("Busca por qtde total de cidade por estado não localizados.");
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setDados(lista);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 5
	 * @param PathVariable codigoIbge
	 */
	@GetMapping(value="/ibge/{codigoIbge}")
	public ResponseEntity<Response<CidadeDto>> buscarPorCodIbge(@PathVariable int codigoIbge) {
		log.info("Buscar cidade por codIbge: {}", codigoIbge);
		Response<CidadeDto> response = new Response<>();
		
		Optional<Cidade> cidade = cidadeService.buscarPorCodigoIbge(codigoIbge);

		if(!cidade.isPresent()) {
			log.info("Busca de cidade por código ibge não localizada.");
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setDados(this.converterCidadeParaCidadeDto(cidade.get()));
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 6 
	 * @param uf Estado
	 */
	@GetMapping(value="/uf/{uf}")
	public ResponseEntity<Response<List<String>>> buscarNomesPorUf(@PathVariable("uf") String uf){
		log.info("Buscar nomes das cidades do estado. Uf: ", uf);
		Response<List<String>> response = new Response<>();
		List<String> nomesCidades = new ArrayList<>();
		
		List<Cidade> cidades = cidadeService.buscarPorUf(uf);
		
		if(!cidades.isEmpty()) 
			cidades.forEach(c -> nomesCidades.add(c.getNome()));
		else {
			log.info("Cidades não localizadas para o uf: {}.", uf);
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}
			
		response.setDados(nomesCidades);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 7
	 * @param RequestBody CidadeDto
	 */
	@PostMapping
	public ResponseEntity<Response<CidadeDto>> cadastrarCidade(@Valid @RequestBody CidadeDto cidadeDto, BindingResult result) {		
		log.info("Cadastrar cidade: {}", cidadeDto.toString());
		Response<CidadeDto> response = new Response<CidadeDto>();
				
		if (cidadeService.buscarPorCodigoIbge(cidadeDto.getCodigoIbge()).isPresent()) {
			result.addError(new ObjectError("cidade", "Cidade já cadastrada na base de dados"));
		}
		
		if(result.hasErrors()) {
			log.info("Erro validando dados do cadastro de cidade: {}", cidadeDto.toString());
			result.getAllErrors().forEach( e -> response.getErros().add(e.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}	
		
		Cidade cidade = cidadeService.persistir(this.converterCidadeDtoParaCidade(cidadeDto, result));
		response.setDados(this.converterCidadeParaCidadeDto(cidade));		
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 8
	 * @param PathVariable id
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<CidadeDto>> deletar(@PathVariable int id) {
		log.info("Deletar cidade id: {}", id);
		Response<CidadeDto> response = new Response<>();
		
		if (!cidadeService.buscarPorId(id).isPresent()) {
			log.error("Impossível excluir cidade. Cadastro não localizado. Id: {}.", id);
			response.getErros().add("Nenhum resultado foi encontrado para o ID requisitado.");
			return ResponseEntity.badRequest().body(response);
		}	
		
		Optional<Cidade> cidade = cidadeService.deletar(id);
		cidade.ifPresent(c -> response.setDados(this.converterCidadeParaCidadeDto(c)));
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 9
	 * @param RequestBody filtroDto
	 */
	@GetMapping(value="/filtro")
	public ResponseEntity<Response<List<CidadeDto>>> buscarPorFiltroColuna(@Valid @RequestBody FiltroDto filtroDto, BindingResult result) {
		log.info("Buscar cidades utilizando o filtro de coluna com valor. Filtro {}", filtroDto.toString());
		Response<List<CidadeDto>> response = new Response<>();
		List<CidadeDto> cidadesDto = new ArrayList<>();
		
		String coluna = CidadeInt.mapaColunas(filtroDto.getColuna());
		if(coluna.isEmpty())
			result.addError(new ObjectError("coluna", "Valor do campo 'coluna' inválido."));	
		
		if(result.hasErrors()) {
			log.info("Erro validando dados do filtro: {}", filtroDto.toString());
			result.getAllErrors().forEach( e -> response.getErros().add(e.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		
		List<Cidade> cidades = cidadeService.buscarPorFiltroColunaValor(coluna, filtroDto.getValor());
		
		if(!cidades.isEmpty()) 
			cidades.forEach(c -> cidadesDto.add(this.converterCidadeParaCidadeDto(c)));		
		else {
			log.info("Cidades não localizadas para o filtro: {} = {}", filtroDto.getColuna(), filtroDto.getValor());
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
			return ResponseEntity.badRequest().body(response);
		}	
		
		response.setDados(cidadesDto);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 10
	 * @param PathVariable coluna
	 */
	@GetMapping(value="/filtro/{coluna}")
	public ResponseEntity<Response<HashMap<String,Long>>> buscarRegistrosUnicosPorFiltroColuna(@PathVariable String coluna) {
		log.info("Buscar registros únicos utilizando o filtro de coluna. Coluna: {}", coluna);
		Response<HashMap<String,Long>> response = new Response<>();		
		HashMap<String,Long> totalRegistros = new HashMap<>();
		
		String colunaMap = CidadeInt.mapaColunas(coluna);
		if(colunaMap.isEmpty()) 
			response.getErros().add("Coluna inválida");
		
		List<Object> lista = cidadeService.buscarRegistrosUnicosPorFiltroColuna(colunaMap);
		
		if(!lista.isEmpty()) 
			totalRegistros.put("qtde", (long) lista.size());
		else {
			log.info("Nenhum registro localizado para o filtro de coluna: {}.", coluna);
			response.getErros().add("Nenhum resultado foi encontrado para a pesquisa solicitada.");
		}	
		
		if(!response.getErros().isEmpty())  
			return ResponseEntity.badRequest().body(response);
				
		response.setDados(totalRegistros);
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Exercício 11
	 */
	@GetMapping(value="/qtdeTotal")
	public ResponseEntity<Response<HashMap<String,Long>>> qtdeRegistros() {
		log.info("Buscar qtde total de cidades");
		Response<HashMap<String,Long>> response = new Response<>();
		response.setDados(cidadeService.buscarQtdeRegistros());
		return ResponseEntity.ok(response);
	}
	
		
	/***
	 * Exercício 12	
	 */
	@GetMapping(value="/mais_distantes")
	public ResponseEntity<Response<List<Cidade>>> buscarCidadesMaisDistantes() {
		log.info("Buscar cidades mais dintantes entre si.");
		Response<List<Cidade>> response = new Response<>();
		List<Cidade> cidades = cidadeService.buscarCidadesMaisDistantes();
		
		if(cidades.isEmpty()) {
			String msgErro = "Falha ao identificar as cidades com maior distancia entre si.";
			log.error(msgErro);
			throw new ApiDefaultException(msgErro);
		}
		response.setDados(cidades);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	/***
	 * Converte uma cidadeDto para o tipo Cidade
	 * @param cidadeDto
	 * @param result
	 * @return Cidade
	 */
	private Cidade converterCidadeDtoParaCidade(CidadeDto cidadeDto, BindingResult result) {
		Cidade cidade = new Cidade();
		cidade.setNome(cidadeDto.getNome());
		cidade.setcodigoIbge(cidadeDto.getCodigoIbge());
		cidade.setCapital(cidadeDto.isCapital());
		cidade.setId(cidadeDto.getId());
		cidade.setUf(cidadeDto.getUf());
		cidadeDto.getNomeAlternativo().ifPresent(v -> cidade.setNomeAlternativo(v));
		cidadeDto.getNomeSemAcento().ifPresent(v -> cidade.setNomeSemAcento(v));
		cidadeDto.getMicroRegiao().ifPresent(v -> cidade.setMicroRegiao(v));
		cidadeDto.getMesoRegiao().ifPresent(v -> cidade.setMesoRegiao(v));
		cidadeDto.getLatitude().ifPresent(v -> cidade.setLatitude(v));
		cidadeDto.getLatitude().ifPresent(v -> cidade.setLongitude(v));
		return cidade;
	}
	
	
	/***
	 * Converte uma cidade para o tipo CidadeDto
	 * @param cidade
	 * @return CidadeDto
	 */
	private CidadeDto converterCidadeParaCidadeDto(Cidade cidade) {
		CidadeDto cidadeDto = new CidadeDto();
		cidadeDto.setNome(cidade.getNome());
		cidadeDto.setCapital(cidade.isCapital());
		cidadeDto.setCodigoIbge(cidade.getcodigoIbge());
		cidadeDto.setId(cidade.getId());
		cidadeDto.setUf(cidade.getUf());
		cidadeDto.setNomeAlternativo(Optional.ofNullable(cidade.getNomeAlternativo()));
		cidadeDto.setNomeSemAcento(Optional.ofNullable(cidade.getNomeSemAcento()));
		cidadeDto.setLatitude(Optional.ofNullable(cidade.getLatitude()));
		cidadeDto.setLongitude(Optional.ofNullable(cidade.getLongitude()));
		cidadeDto.setMesoregiao(Optional.ofNullable(cidade.getMesoRegiao()));
		cidadeDto.setMicroregiao(Optional.ofNullable(cidade.getMicroRegiao()));
		return cidadeDto;
	}
	
	
}

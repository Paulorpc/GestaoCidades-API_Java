package com.paulorpc.cidades.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paulorpc.cidades.api.config.ArquivoConfig;
import com.paulorpc.cidades.api.entities.Cidade;
import com.paulorpc.cidades.api.exceptions.ApiDefaultException;
import com.paulorpc.cidades.api.services.Impl.CidadeServiceImpl;

public class Util {
	
	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	public static ArquivoConfig getCaminhoArquivos() {
		return new ArquivoConfig();		
	}
		
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
	
	/***
	 * 
	 * @param List<Object> Lista de entidades para conversão
	 * @param caminhoArquivo Caminho do arquivo gerado 
	 * @return SintringWriter conteúdo do arquivo gerado
	 * @throws Exception ApiDefaultException
	 */
	public static StringWriter csv2json(List<Object> entidade, String caminhoArquivo) throws Exception {
        
        File caminhoSaida = new File(caminhoArquivo);
        StringWriter conteudo = new StringWriter();
        
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.enable(SerializationFeature.INDENT_OUTPUT);
        	mapper.writeValue(caminhoSaida, entidade);
        	mapper.writeValue(conteudo, entidade);
        }
        catch(Exception e){
        	String msgErro="Erro ao gerar arquivo JSON";
        	log.error(msgErro, e);
        	throw new ApiDefaultException(msgErro, e);
        	
        }        
		return conteudo;
    }
	

	/***
	 * 
	 * @param caminhoArquivo Caminho do arquivo .CSV
	 * @param separador tipo de separador usado no arquivo .CSV
	 * @return List<Cidade> Cidades armazenadas no arquivo
	 * @throws Exception ApiDefaultException
	 */
	@SuppressWarnings("resource")
	public static List<Cidade> gerarCidadesDoArquivoCsv(Path caminhoArquivo, String nome, String separador) {
        String caminho = caminhoArquivo.resolve(nome).toAbsolutePath().normalize().toString();        
        Pattern pattern = Pattern.compile(separador);
        List<Cidade> cidades = new ArrayList<Cidade>();

        try {        	
        	BufferedReader in = new BufferedReader(new FileReader(new File(caminho)));
        	cidades = in.lines().skip(1).map( line -> { String[] s = pattern.split(line);
																 Cidade c = new Cidade();        															 
																 c.setcodigoIbge( Integer.parseInt( s[0]) );
																 c.setUf(s[1]);
																 c.setNome(s[2]);
																 c.setCapital( new Boolean(s[3])  );
																 c.setLongitude( new BigDecimal(s[4]) );
																 c.setLatitude( new BigDecimal(s[5]) );
																 c.setNomeSemAcento(s[6]);
																 c.setNomeAlternativo(s[7]);
																 c.setMicroRegiao(s[8]);
																 c.setMesoRegiao(s[9]);
														return c;
													   }).collect( Collectors.toList() );
        }
        catch(Exception e){
        	String msgErro="Erro ao gerar lista de cidade a partir do arquivo csv. " +e.getMessage();
        	log.error(msgErro, e);
        	e.printStackTrace();
        	throw new ApiDefaultException(msgErro, e);
        }
        
		return cidades;
    }

}

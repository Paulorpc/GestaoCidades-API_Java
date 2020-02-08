package com.paulorpc.cidades.api.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface ArquivoService {
	
	/***
	 * Armazena um arquivo enviado por upload no servidor local
	 * @param arquivo
	 * @return String nomeArquivo
	 * @throws Exception 
	 */
	public String armazenar(MultipartFile arquivo) throws Exception;	
		
	
	/***
	 * Retorna o resource do arquivo
	 * @param nomeArquivo
	 * @return Resource
	 * @throws Exception
	 */
	public Resource carregarComoResource(String nomeArquivo) throws Exception;

}

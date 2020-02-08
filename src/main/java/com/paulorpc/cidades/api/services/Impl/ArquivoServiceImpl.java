package com.paulorpc.cidades.api.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.paulorpc.cidades.api.exceptions.ApiDefaultException;
import com.paulorpc.cidades.api.services.ArquivoService;
import com.paulorpc.cidades.api.utils.Util;


@Service
public class ArquivoServiceImpl implements ArquivoService {
	
	private static final Logger log = LoggerFactory.getLogger(ArquivoServiceImpl.class);
	
	private Path repositorioPadrao;
	
	@Autowired
    public ArquivoServiceImpl() {
        try {
        	log.info("Criando pasta para upload dos arquivos enviados.");
        	repositorioPadrao = Util.getCaminhoArquivos().getRespositorioPadrao();
        	Files.createDirectories(repositorioPadrao.toAbsolutePath().normalize());
        	Files.createDirectories(Util.getCaminhoArquivos().getRepositorioCidades().toAbsolutePath().normalize());
        } catch (Exception e) {
        	log.error("Falha ao criar pasta para upload dos arquivos enviados.");
            throw new ApiDefaultException("Erro ao criar o diretório de armazenamento de arquivos: " + e.getMessage());
        }
    }
	
	@Override
	public String armazenar(MultipartFile arquivo, Path caminhoArquivo) {
		log.info("Armazenando arquivo no servidor.");
		String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
			
		if(nomeArquivo.contains("..")) 
			throw new ApiDefaultException("Nome do arquivo inválido: " + nomeArquivo);
		
		if (caminhoArquivo == null)
			caminhoArquivo = repositorioPadrao;

		try {
			Path localDestino = caminhoArquivo.resolve(nomeArquivo).normalize();
		    Files.copy(arquivo.getInputStream(), localDestino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		    throw new ApiDefaultException("Erro ao armazenar arquivo" + nomeArquivo);
		}
		
		return nomeArquivo;
	}
	
	
	
	@Override
	public Resource carregarComoResource(String nomeArquivo) throws Exception {
        
        Path caminho = repositorioPadrao.resolve(nomeArquivo).normalize();
        Resource resource = new UrlResource(caminho.toUri());
        
        if(!resource.exists())                        
            throw new ApiDefaultException("Arquivo não encontrado: " + nomeArquivo);
        
        return resource;
	}
	
}

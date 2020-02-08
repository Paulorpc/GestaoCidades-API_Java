package com.paulorpc.cidades.api.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

@Service
public class ArquivoServiceImpl implements ArquivoService {
	
	private static final Logger log = LoggerFactory.getLogger(ArquivoServiceImpl.class);
	
	private Path localArmazenamento;
	
	@Autowired
    public ArquivoServiceImpl() {
        try {
        	log.info("Criando pasta para upload dos arquivos enviados.");
        	//TODO adicionar caminho num arquivo de configuração ou variável de ambiente.  
        	this.localArmazenamento = Paths.get("/home/pcezar/eclipse-workspace/GestaoCidades/arquivosEnviados").toAbsolutePath().normalize();
            Files.createDirectories(this.localArmazenamento);
        } catch (Exception e) {
        	log.error("Falha ao criar pasta para upload dos arquivos enviados.");
            throw new ApiDefaultException("Erro ao criar o diretório de armazenamento de arquivos: " + e.getMessage());
        }
    }
	
	@Override
	public String armazenar(MultipartFile arquivo) {
		log.info("Armazenando arquivo no servidor.");
		String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
			
		if(nomeArquivo.contains("..")) 
			throw new ApiDefaultException("Nome do arquivo inválido: " + nomeArquivo);

		try {
			Path localDestino = this.localArmazenamento.resolve(nomeArquivo);
		    Files.copy(arquivo.getInputStream(), localDestino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		    throw new ApiDefaultException("Erro ao armazenar arquivo" + nomeArquivo);
		}
		
		return nomeArquivo;
	}
	
	
	
	@Override
	public Resource carregarComoResource(String nomeArquivo) throws Exception {
        
        Path caminho = this.localArmazenamento.resolve(nomeArquivo).normalize();
        Resource resource = new UrlResource(caminho.toUri());
        
        if(!resource.exists())                        
            throw new ApiDefaultException("Arquivo não encontrado: " + nomeArquivo);
        
        return resource;
	}
	
}

package com.paulorpc.cidades.api.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoConfig {

	private final Path repositorioPadrao;
	private final Path repositoriCidades;
	
	public ArquivoConfig() {
		//defaultPath = "/home/pcezar/eclipse-workspace/GestaoCidades/.../";
		repositorioPadrao = Paths.get("arquivosEnviados").normalize();
		repositoriCidades = Paths.get("arquivosEnviados", "cidades").normalize();
	}

	public Path getRespositorioPadrao() {
		return repositorioPadrao;
	}

	public Path getRepositorioCidades() {
		return repositoriCidades;
	}

	
}


package br.com.danilo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.dto.Retorno;
import br.com.danilo.exception.WppException;
import br.com.danilo.negocio.ContatoNeg;

@Service
public class ContatoService {

	@Autowired
	private ContatoNeg contatoNeg;

	public Retorno salvarArquivo(MultipartFile file) throws WppException, IOException {
		
		contatoNeg.salvarArquivo(file);

		return Retorno.builder().mensagem("arquivo salvo com sucesso!").build();

	}

}

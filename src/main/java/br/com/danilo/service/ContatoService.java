package br.com.danilo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.dto.Retorno;
import br.com.danilo.exception.WppException;

@Service
public class ContatoService {
	
	@Autowired ImportacaoService importacaoService;
	
	public Retorno salvarArquivo(MultipartFile file) throws IOException, WppException {
		return importacaoService.salvar(file);
	}
	
}

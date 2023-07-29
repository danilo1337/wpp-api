package br.com.danilo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.dto.Retorno;
import br.com.danilo.exception.WppException;
import br.com.danilo.service.ContatoService;
import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RestController
@RequestMapping("contatos")
public class ContatoController {

	@Autowired
	private ContatoService service;

	@ApiOperation(value = "Upload de arquivo de contatos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PostMapping(value = "arquivo/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException, WppException {
			
		if (!file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Retorno.builder()
									 .mensagem("Apenas arquivos CSV são permitidos para o upload")
									 .build());
		}
		
		Retorno retorno = service.salvarArquivo(file);

		return ResponseEntity.ok(retorno);
	}
	
}

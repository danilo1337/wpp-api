package br.com.danilo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.dto.Retorno;
import br.com.danilo.exception.WppException;
import br.com.danilo.service.ImportacaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("arquivos")
public class ArquivoController {
	
	@Autowired
	private ImportacaoService service;
	
	@ApiOperation(value = "Upload de arquivo de contatos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws WppException, IOException  {
		
		service.salvar(file);

		return ResponseEntity.ok(Retorno.builder()
					   				    .mensagem("arquivo salvo com sucesso!")
					   				    .build());
	}
	
	@GetMapping("{id}/download")
	public ResponseEntity<?> downloadArquivo(@PathVariable Long id) throws WppException {
		return service.downloadArquivo(id);
	}
	
	
	@GetMapping("{id}/visualizar")
	public ResponseEntity<?> visualizarArquivo(@PathVariable Long id) throws WppException {
		return service.visualizarArquivo(id);
	}	
	
}

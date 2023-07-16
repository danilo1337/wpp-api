package br.com.danilo.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.dto.Retorno;
import br.com.danilo.entity.Importacao;
import br.com.danilo.entity.Usuario;
import br.com.danilo.exception.WppException;
import br.com.danilo.repository.ImportacaoRepository;
import br.com.danilo.repository.UsuarioRepository;
import br.com.danilo.util.UtilGZIP;

@Service
public class ImportacaoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ImportacaoRepository importacaoRep;
	
	final String EMAIL_USUARIO_WEB = "a@a.com";

	public Retorno salvar(MultipartFile file) throws WppException, IOException {

		Importacao importacao = Importacao.builder()
								.nomeArquivo(file.getOriginalFilename())
								.arquivoBase64(comprimir(file.getBytes()))
								.build();

		Usuario usuario = usuarioRepository.findByEmail(EMAIL_USUARIO_WEB)
		.orElseThrow(() -> new WppException("Usuário não encontrado!", HttpStatus.BAD_REQUEST));

		importacao.setUsuario(usuario);

		importacaoRep.save(importacao);
		
		return Retorno.builder()
				.mensagem("arquivo salvo com sucesso!")
				.build();
	}

	public Importacao consultar(Long id) throws WppException {
		Importacao importacao = importacaoRep.findById(id)
		.orElseThrow(() -> new WppException("Arquivo não encontrado", HttpStatus.BAD_REQUEST));
		
		return importacao;
	}
	
	
	public ResponseEntity<?> downloadArquivo(Long id) throws WppException {
		
		Importacao importacao = consultar(id);
		
		byte[] responseBody = descomprimir(importacao.getArquivoBase64());
		
	    HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+importacao.getNomeArquivo());
        
		return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
	}
	
	public ResponseEntity<?> visualizarArquivo(Long id) throws WppException {
		
		Importacao importacao = consultar(id);
		
		if (!importacao.getNomeArquivo().toLowerCase().endsWith(".pdf")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Retorno.builder()
									 .mensagem("Apenas arquivos pdf são permitidos visualizar")
									 .build());
		}
		
		byte[] responseBody = descomprimir(importacao.getArquivoBase64());
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
		
		return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
		
	}
	
	public String comprimir(byte[] arquivoOriginal) {
		byte compresso[] = UtilGZIP.compressGzip(arquivoOriginal);
		String base64 = Base64.getEncoder().encodeToString(compresso);
		return base64;
	}

	public byte[] descomprimir(String base64) {
		byte[] compresso = Base64.getDecoder().decode(base64);
		byte[] arquivoOriginal = UtilGZIP.decompressGzip(compresso);
		return arquivoOriginal;
	}

}

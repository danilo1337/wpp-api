package br.com.danilo.negocio;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.danilo.entity.Importacao;
import br.com.danilo.exception.WppException;
import br.com.danilo.service.ImportacaoService;
import br.com.danilo.util.UtilCSV;

@Component
public class ContatoNeg {

	@Autowired
	private ImportacaoService importacaoService;
	
	@Value("${topico.contatos}")
	private String urlTopicoContatos;
	
	public void salvarArquivo(MultipartFile file) throws WppException, IOException {

		if(!ehArquivoValido(file)) {
			throw new WppException("O Arquivo CSV não está nos padrões. Verifique as colunas [ cpf_cnpj;nome;telefone;observacao ].", HttpStatus.BAD_REQUEST);
		}

		Importacao importacao = importacaoService.salvar(file);
		
		//Request producer kafka
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpEntity<String> entity = new HttpEntity<>(importacao.getArquivoBase64(), headers);
        ResponseEntity<String> response = restTemplate.exchange(urlTopicoContatos, HttpMethod.POST, entity, String.class); 
        
	}
	
	/**
	 * Verifica se o arquivo CSV enviado está no padrão esperado.
	 *
	 * O padrão esperado para o arquivo CSV é:
	 * - As colunas devem estar separadas por ponto e vírgula (;).
	 * - As colunas devem estar nessa ordem: cpf_cnpj, nome, telefone e observacao.
	 *
	 * O padrão esperado é: cpf_cnpj;nome;telefone;observacao
	 *
	 * @param file O arquivo CSV enviado como MultipartFile.
	 * @return true se o arquivo estiver no padrão esperado, caso contrário, retorna false.
	 */
	private boolean ehArquivoValido(MultipartFile file) {
		try {

			UtilCSV utilCSV = new UtilCSV();
			List<String> duasLinhas = utilCSV.lerDuasLinhas(file);
			
			if(duasLinhas.isEmpty() || duasLinhas.size() < 2)
				return false;
			 
			 int qtdColunasCabecalho = duasLinhas.get(0).split(";").length;
			 int qtdColunasPrimeiraLinha = duasLinhas.get(1).split(";").length;
		
			 if(qtdColunasCabecalho != 4 || qtdColunasPrimeiraLinha != 4 )
				 return false;
			 
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}

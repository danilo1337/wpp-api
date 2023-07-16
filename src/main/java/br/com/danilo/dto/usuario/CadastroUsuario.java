package br.com.danilo.dto.usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CadastroUsuario {
	
	private String nome;
	
	private String senha;
	
	private String email;
	
}

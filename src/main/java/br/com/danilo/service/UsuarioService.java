package br.com.danilo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilo.entity.Usuario;
import br.com.danilo.exception.WppException;
import br.com.danilo.negocio.UsuarioNeg;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioNeg usuarioNeg;

	public void cadastrarUsuario(Usuario usuario) {
		try {
			usuarioNeg.cadastrarUsuario(usuario);
		} catch (WppException e) {
			
		}
	}
	
	public void alterarStatusUsuario(Usuario usuario) {
		try {
			usuarioNeg.alterarStatusUsuario(usuario);
		} catch (WppException e) {
			
		}
	}

}

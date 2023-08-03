package br.com.danilo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.danilo.entity.Usuario;
import br.com.danilo.enums.EnumSimNao;
import br.com.danilo.exception.WppException;
import br.com.danilo.repository.UsuarioRepository;

@Component
public class UsuarioNeg {
	@Autowired
	private UsuarioRepository rep;

	public void cadastrarUsuario(Usuario usuario) throws WppException {
		usuario.setAtivo(EnumSimNao.N);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		rep.save(usuario);
	}

	public void alterarStatusUsuario(Usuario usuario) throws WppException {

		rep.findById(usuario.getId()).ifPresentOrElse(usuarioEncontrado -> {
			usuarioEncontrado.setAtivo(usuario.getAtivo());
			rep.save(usuarioEncontrado);
		}, () -> new WppException("Usuario não encotrado", HttpStatus.BAD_REQUEST));

	}

}

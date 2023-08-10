package br.com.danilo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.danilo.dto.usuario.ConsultaUsuario;
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
	
	
	public List<ConsultaUsuario> listarUsuarios() {
		List<ConsultaUsuario> consultaUsuarios = new ArrayList<>();
		
		rep.findAll().stream().forEach(e -> {
			
			ConsultaUsuario consultaUsuario = new ConsultaUsuario();
			BeanUtils.copyProperties(e, consultaUsuario);
			consultaUsuarios.add(consultaUsuario);
			
		});

		return consultaUsuarios;
	}

	public Page<ConsultaUsuario> listarUsuarios(String ativo, Pageable pageable) {
		
		Page<Usuario> usuariosPage;
		
		if (StringUtils.hasText(ativo)) {
			usuariosPage = rep.findByAtivo(ativo, pageable);
			return null;
		} else {
			usuariosPage = rep.findAll(pageable);
		}
		
		return usuariosPage.map(this::convertToConsultaUsuario);
		
	}
	
	
	private ConsultaUsuario convertToConsultaUsuario(Usuario usuario) {
		ConsultaUsuario consultaUsuario = new ConsultaUsuario();

		BeanUtils.copyProperties(usuario, consultaUsuario);

		return consultaUsuario;

	}
	
}

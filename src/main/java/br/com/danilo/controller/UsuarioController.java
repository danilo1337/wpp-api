package br.com.danilo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilo.config.constantes.Role;
import br.com.danilo.dto.Retorno;
import br.com.danilo.dto.usuario.StatusUsuario;
import br.com.danilo.dto.usuario.CadastroUsuario;
import br.com.danilo.entity.Usuario;
import br.com.danilo.enums.EnumSimNao;
import br.com.danilo.enums.EnumStatus;
import br.com.danilo.service.UsuarioService;

import static java.lang.String.format;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody CadastroUsuario cadastroUsuario) {

		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(cadastroUsuario, usuario);

		service.cadastrarUsuario(usuario);
		
		Retorno retorno = Retorno.builder()
								 .mensagem("Usuário cadastrado com sucesso!")
								.build();
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize(Role.ADMIN)
	@PutMapping("{id}/status")
	public ResponseEntity<?> alterarStatusUsuario(@PathVariable Long id, @RequestBody StatusUsuario statusUsuario) {
		
		Retorno retorno = Retorno.builder()
				.mensagem(format("Usuário %s com sucesso!", statusUsuario.getStatus().getDescricao()))
				.build();
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setAtivo(statusUsuario.getStatus() == EnumStatus.ATIVADO ? EnumSimNao.S : EnumSimNao.N );
		
		service.alterarStatusUsuario(usuario);
		
		
		return ResponseEntity.ok(retorno);
	}
	
	@PreAuthorize(Role.ADMIN)
	@GetMapping
	public ResponseEntity<?> listarUsuarios() {
		return ResponseEntity.ok(service.listarUsuarios());
	}

}

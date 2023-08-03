package br.com.danilo.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.danilo.entity.Permissao;
import br.com.danilo.entity.Usuario;
import br.com.danilo.enums.EnumSimNao;
import br.com.danilo.repository.PermissaoRepository;
import br.com.danilo.repository.UsuarioRepository;

@Configuration
public class AppUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private PermissaoRepository authorityRepo;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userRepo.findByUsername(username).orElse(null);

		if (usuario == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return getUser(usuario);
	}

	public User getUser(Usuario usuario) {
		List<Permissao> permissoes = authorityRepo.findByUsername(usuario.getUsername());
		Set<SimpleGrantedAuthority> simpleGrantedAuthorities = permissoes.stream()
				.map(g -> new SimpleGrantedAuthority(g.getId().getPermissao())).collect(Collectors.toSet());

		boolean usuarioAtivo = EnumSimNao.S == usuario.getAtivo();

		return new User(usuario.getUsername(), usuario.getSenha(), usuarioAtivo, true, true, true, simpleGrantedAuthorities);
	}

	public List<Usuario> findAll() {
		List<Usuario> list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}

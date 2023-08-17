package br.com.danilo.repository;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import br.com.danilo.entity.Usuario;
import br.com.danilo.enums.EnumSimNao;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByUsername(String username);
	
	Page<Usuario> findByAtivo(EnumSimNao ativo,  Pageable pageable);
	
	Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	Page<Usuario> findByNomeContainingIgnoreCaseAndAtivo(String nome, EnumSimNao ativo,  Pageable pageable);
	
	
	default Page<Usuario> findByAtivo(String nome, Boolean ativo, Pageable pageable) {

	    if (!StringUtils.hasText(nome) && Objects.isNull(ativo)) {
	        return findAll(pageable);
	    }  
	    
    	if (!StringUtils.hasText(nome)) {
    		EnumSimNao usuarioAtivo = ativo ? EnumSimNao.S : EnumSimNao.N;
	        return findByAtivo(usuarioAtivo, pageable);
	    } 
    	
    	if (Objects.isNull(ativo)) {
	        return findByNomeContainingIgnoreCase(nome, pageable);
	    } 
    	
    	EnumSimNao usuarioAtivo = ativo ? EnumSimNao.S : EnumSimNao.N;
    	
        return findByNomeContainingIgnoreCaseAndAtivo(nome, usuarioAtivo, pageable);

	}

}

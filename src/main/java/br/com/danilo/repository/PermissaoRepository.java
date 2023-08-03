package br.com.danilo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, String>{
	
	List<Permissao> findByUsername(String username);
	
}

package br.com.danilo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.entity.Importacao;

public interface ImportacaoRepository extends JpaRepository<Importacao, Long> {
	
	List<Importacao> findByUsuarioId(Long usuarioId);
	
}
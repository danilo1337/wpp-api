package br.com.danilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danilo.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}

package com.javanautas.fakeapius.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javanautas.fakeapius.infrastructure.entities.ProdutoEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
	
	Boolean existsByNome(String nome);
	
	@Transactional
	ProdutoEntity findByNome(String nome);
	
	@Transactional
	void deleteByNome(String nome);
}

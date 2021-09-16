package com.example.gubee.hexagon.domain.adapter;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositoryDB extends JpaRepository<ProdutoDB, Integer>{
	
	@Query("SELECT distinct p FROM Produto p "
			+ "JOIN p.tecnologias t "
			+ "WHERE (COALESCE(:tecnologias, NULL) IS NULL OR t.nome IN :tecnologias) "
			+ "AND (p.mercadoAlvo = :mercadoAlvo OR :mercadoAlvo is NULL) ")
	public List<ProdutoDB> getAllByFilterPersonal(@Param("tecnologias") Set<String> tecnologias, 
			@Param("mercadoAlvo") String mercadoAlvo);

}

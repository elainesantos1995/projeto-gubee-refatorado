package com.example.gubee.hexagon.spi;

import java.util.List;
import com.example.gubee.hexagon.domain.Produto;

public interface ProdutoRepository{

	Produto save(Produto produto);
	void update(int id, Produto produtoNovo);
	void delete(int id);
	Produto getById(int id);
	List<Produto> getAll();
	List<Produto> getAllByFilterPersonal(String tecnologias, String mercadoAlvo);
	
}

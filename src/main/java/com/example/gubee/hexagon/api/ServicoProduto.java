package com.example.gubee.hexagon.api;

import java.util.List;

import com.example.gubee.hexagon.domain.Produto;

public interface ServicoProduto {
	
	public Produto save(Produto produto);
	public void update(int id, Produto produtoNovo);
	public void delete(int id);
	public Produto getById(int id);
	public List<Produto> getAll();
	public List<Produto> getAllByFilterPersonal(String tecnologias, String mercadoAlvo);

}

package com.example.gubee.hexagon.api.impl;

import java.util.List;

import com.example.gubee.hexagon.api.ServicoProduto;
import com.example.gubee.hexagon.domain.Produto;
import com.example.gubee.hexagon.spi.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServicoProdutoImpl implements ServicoProduto{
	
	private final ProdutoRepository repository;

	@Override
	public Produto save(Produto produto) {
		return repository.save(produto);
	}

	@Override
	public void update(int id, Produto produtoNovo) {
		repository.update(id, produtoNovo);		
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}

	@Override
	public Produto getById(int id) {
		return repository.getById(id);
	}

	@Override
	public List<Produto> getAll() {
		return repository.getAll();
	}

	@Override
	public List<Produto> getAllByFilterPersonal(String tecnologias, String mercadoAlvo) {
		
		if((tecnologias == null || tecnologias.equals("")) && (mercadoAlvo == null || mercadoAlvo.equals(""))) {						
			return this.repository.getAll();
		} else {
			return this.repository.getAllByFilterPersonal(tecnologias, mercadoAlvo);
		}
	}

}

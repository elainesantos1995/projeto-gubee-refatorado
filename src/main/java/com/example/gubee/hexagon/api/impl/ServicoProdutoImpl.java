package com.example.gubee.hexagon.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gubee.hexagon.api.ServicoProduto;
import com.example.gubee.hexagon.domain.Produto;
import com.example.gubee.hexagon.spi.ProdutoRepository;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//@Service
public class ServicoProdutoImpl implements ServicoProduto{
	
	@Autowired
	private ProdutoRepository repository;

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
		return repository.getAllByFilterPersonal(tecnologias, mercadoAlvo);
	}

}

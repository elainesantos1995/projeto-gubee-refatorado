package com.example.gubee.hexagon.spi.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.gubee.hexagon.domain.Produto;
import com.example.gubee.hexagon.domain.adapter.ProdutoDB;
import com.example.gubee.hexagon.domain.adapter.ProdutoRepositoryDB;
import com.example.gubee.hexagon.spi.ProdutoRepository;

@Service
public class ProdutoRepositoryImpl implements ProdutoRepository{
	
	private ProdutoRepositoryDB repository;
	
	@Autowired
    public void setProdutoRepositoryImpl(ProdutoRepositoryDB repository){
          this.repository = repository;
    }

	@Override
	public Produto save(Produto produto) {
		ProdutoDB produtoDB = ProdutoDB.builder()
				.nomeProduto(produto.getNomeProduto())
				.descricaoSimples(produto.getDescricaoSimples())
				.mercadoAlvo(produto.getMercadoAlvo())
				.build();
		
		produtoDB = repository.save(produtoDB);
		return produtoDB.parseProduto();
	}

	@Override
	public void update(int id, Produto produtoNovo) {
		
		ProdutoDB produtoDB = ProdutoDB.builder()
				.nomeProduto(produtoNovo.getNomeProduto())
				.descricaoSimples(produtoNovo.getDescricaoSimples())
				.mercadoAlvo(produtoNovo.getMercadoAlvo())
				.build();
		
		Optional<ProdutoDB> produto = repository.findById(id);

		if (produto.isPresent()) {
			produtoDB.setId(id);
			produtoNovo = repository.save(produtoDB).parseProduto();
		} else {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Produto não encontrado!");
			throw exception;
		}
	}

	@Override
	public void delete(int id) {
		this.repository.findById(id).map(produto -> {
			this.repository.delete(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
	}

	@Override
	public Produto getById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!")).parseProduto();
	}

	@Override
	public List<Produto> getAll() {
		List<ProdutoDB> produtosDB = this.repository.findAll();		
		return this.toListProdutos(produtosDB);		
	}

	@Override
	public List<Produto> getAllByFilterPersonal(String tecnologias, String mercadoAlvo) {
		List<String> items = new ArrayList<>();
		if(tecnologias != null) {
			items = Arrays.asList(tecnologias.split("\\s*,\\s*"));			
		}
		Set<String> set = new HashSet<>(items);
		
		List<ProdutoDB> produtosDB = repository.getAllByFilterPersonal(set, mercadoAlvo);
			
		return this.toListProdutos(produtosDB);
	}
	
	private List<Produto> toListProdutos(List<ProdutoDB> produtosDB){
		List<Produto> produtos = new ArrayList<>();
		for (ProdutoDB produtoDB : produtosDB) {
			produtos.add(produtoDB.parseProduto());
		}
		
		return produtos;
	}
	
}

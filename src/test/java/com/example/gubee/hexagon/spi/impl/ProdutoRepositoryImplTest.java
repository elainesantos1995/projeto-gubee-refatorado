package com.example.gubee.hexagon.spi.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.gubee.hexagon.domain.Produto;
import com.example.gubee.hexagon.domain.Tecnologia;
import com.example.gubee.hexagon.domain.adapter.ProdutoDB;
import com.example.gubee.hexagon.domain.adapter.ProdutoRepositoryDB;
import com.example.gubee.hexagon.domain.adapter.TecnologiaDB;
import com.example.gubee.hexagon.domain.adapter.TecnologiaRepositoryDB;

@ExtendWith(MockitoExtension.class)
class ProdutoRepositoryImplTest {
	
	@Mock
	ProdutoRepositoryDB produtoRepository;
	
	@Mock
	TecnologiaRepositoryDB tecnologiaRepository;	
	
	@Autowired
	@InjectMocks
	ProdutoRepositoryImpl produtoService;
	
	@Autowired
	@InjectMocks
	TecnologiaRepositoryImpl tecnologiaService;
	
	private ProdutoDB produtoDB1;
	private ProdutoDB produtoDB2;
	private List<TecnologiaDB> tecsDB1;
	private List<TecnologiaDB> tecsDB2;

	private Produto produto1;
	private Produto produto2;	

	@BeforeEach
	void setUp() {
				
		produtoDB1 = gerarProdutosDB().get(0);
		produtoDB2 = gerarProdutosDB().get(1);
		
		produto1 = gerarProdutos().get(0);
		produto2 = gerarProdutos().get(1);
}

	@Test
	void deveriaTrazerProdutoFiltradoPorUmaTecnologia() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB1);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("MySql", "");
		
		assertEquals(prod.get(0).getNomeProduto(), "Produto 1");
	}
	
	@Test
	void deveriaTrazerProdutoFiltradoPorMaisDeUmaTecnologia() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB1);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("MySql,Java", "");
		
		assertEquals(prod.get(0).getNomeProduto(), "Produto 1");
		assertEquals(1, prod.size());
	}
	
	@Test
	void deveriaTrazerMaisDeUmProdutoFiltradosPorUmaTecnologia() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB1);
		produtosDB.add(produtoDB2);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("MySql", "");
		
		assertEquals(2, prod.size());
		assertEquals(prod.get(0).getNomeProduto(), "Produto 1");
		assertEquals(prod.get(1).getNomeProduto(), "Produto 2");
	}
	
	@Test
	void deveriaTrazerProdutoFiltradosApenasPorMecadoAlvo() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB2);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("", "Alimentação");
		
		assertEquals(prod.get(0).getNomeProduto(), "Produto 2");
	}
	
	@Test
	void deveriaTrazerProdutoFiltradosPorTecnologiasEMercadoAlvo() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB1);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("Java", "Alimentação");
		
		assertEquals(prod.get(0).getNomeProduto(), "Produto 1");
	}
	
	@Test
	void deveriaTrazerProdutoFiltradosPorTecnologiaNaoCadastradaEMercadoAlvoExistente() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		produtosDB.add(produtoDB1);
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("Lua", "Alimentação");
		
		assertEquals(prod.size(), 1);
	}
	
	@Test
	void deveriaNaoTrazerProdutoFiltradoPorTecnologiasNaoCadastradaEMercadoAlvoNaoExistente() {
		
		List<ProdutoDB> produtosDB = new ArrayList<ProdutoDB>();
		
		Mockito.when(produtoRepository.getAllByFilterPersonal(Mockito.anySet(), Mockito.anyString())).thenReturn(produtosDB);	   
		
		List<Produto> prod = produtoService.getAllByFilterPersonal("Lua", "Comércio");
		
		assertEquals(prod.size(), 0);
	}
	
	@Test
	void deveriaTrazerProdutoPorId() {
			 
		Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(produtoDB1));
		
		assertThat(produtoService.getById(produto1.getId()).getMercadoAlvo()).isEqualTo("Alimentação");
	}
	
	
	private List<ProdutoDB> gerarProdutosDB(){
		
		List<ProdutoDB> produtosDB = new ArrayList<>();
		List<TecnologiaDB> tecnologiasDB1 = new ArrayList<>();
		List<TecnologiaDB> tecnologiasDB2 = new ArrayList<>();
		
		TecnologiaDB tecnologiaDB1 = TecnologiaDB.builder()
				.id(1)
				.nome("MySql")
				.build();
		
		TecnologiaDB tecnologiaDB2 = TecnologiaDB.builder()
				.id(1)
				.nome("Java")
				.build();
		
		TecnologiaDB tecnologiaDB3 = TecnologiaDB.builder()
				.id(1)
				.nome("Angular")
				.build();
		
		tecnologiasDB1.add(tecnologiaDB1);
		tecnologiasDB1.add(tecnologiaDB2);
		tecnologiasDB2.add(tecnologiaDB1);
		tecnologiasDB2.add(tecnologiaDB3);
		
		ProdutoDB produtoDB1 = ProdutoDB.builder()
				.id(1).nomeProduto("Produto 1")
				.descricaoSimples("Projeto para back")
				.mercadoAlvo("Alimentação")
				.tecnologias(tecsDB1)
				.build();
			
		ProdutoDB produtoDB2 = ProdutoDB.builder()
					.id(2).nomeProduto("Produto 2")
					.descricaoSimples("Projeto back e front")
					.mercadoAlvo("Transportes")
					.tecnologias(tecsDB2)
					.build();
			
			produtosDB.add(produtoDB1);
			produtosDB.add(produtoDB2);
			
			return produtosDB;
	}
	
	private List<Produto> gerarProdutos(){
		
		List<Produto> produtos = new ArrayList<>();
		List<Tecnologia> tecs1 = new ArrayList<>();
		List<Tecnologia> tecs2 = new ArrayList<>();
		
		Tecnologia t1 = Tecnologia.builder().id(1).nome("Java").build();

		Tecnologia t2 = Tecnologia.builder().id(2).nome("MySql").build();
		
		Tecnologia t3 = Tecnologia.builder().id(3).nome("Angular").build();
		
		tecs1.add(t1);
		tecs1.add(t2);

		tecs2.add(t1);
		tecs2.add(t3);
		
		produto1 = Produto.builder()
				.id(1).nomeProduto("Produto 1")
				.descricaoSimples("Projeto para back")
				.mercadoAlvo("Alimentação")
				.tecnologias(tecs1)
				.build();

		produto2 = Produto.builder()
				.id(2).nomeProduto("Produto 2")
				.descricaoSimples("Projeto back e front")
				.mercadoAlvo("Transportes")
				.tecnologias(tecs2)
				.build();
		
		produtos.add(produto1);
		produtos.add(produto2);
		
		return produtos;

	}

}

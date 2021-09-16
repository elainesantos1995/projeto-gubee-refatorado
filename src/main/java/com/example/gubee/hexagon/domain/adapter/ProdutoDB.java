package com.example.gubee.hexagon.domain.adapter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.gubee.hexagon.domain.Produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProdutoDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private int id;
	@Getter @Setter
	private String nomeProduto;
	@Getter @Setter
	private	String descricaoSimples;
	@Getter @Setter
	private String mercadoAlvo;
	
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="tecnologias_do_produto", joinColumns=
    {@JoinColumn(name="produto_id")}, inverseJoinColumns=
    {@JoinColumn(name="tecnologia_id")})	
	@Getter @Setter
	private List<TecnologiaDB> tecnologias;
	
	public Produto parseProduto() {
		return Produto.builder()
				.id(this.id)
				.descricaoSimples(this.descricaoSimples)
				.nomeProduto(this.nomeProduto)
				.mercadoAlvo(this.mercadoAlvo)
				.build();
	}
	

}

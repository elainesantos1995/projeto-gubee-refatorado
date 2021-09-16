package com.example.gubee.hexagon.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Produto {
	
	@Getter @Setter
	private int id;
	@Getter @Setter
	private String nomeProduto;
	@Getter @Setter
	private	String descricaoSimples;
	@Getter @Setter
	private String mercadoAlvo;	
	@Getter @Setter
	private List<Tecnologia> tecnologias;
	
}

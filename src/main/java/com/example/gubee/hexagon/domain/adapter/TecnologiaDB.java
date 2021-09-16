package com.example.gubee.hexagon.domain.adapter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.gubee.hexagon.domain.Tecnologia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tecnologia")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TecnologiaDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private int id;
	@Getter @Setter
	private String nome;
	
	public Tecnologia parseTecnologia() {
		return Tecnologia.builder()
				.id(this.id)
				.nome(this.nome)
				.build();
	}
}
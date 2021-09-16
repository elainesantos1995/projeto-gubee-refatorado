package com.example.gubee.hexagon.domain;

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
public class Tecnologia {
	
	@Getter
	private int id;
	@Getter @Setter
	private String nome;

}

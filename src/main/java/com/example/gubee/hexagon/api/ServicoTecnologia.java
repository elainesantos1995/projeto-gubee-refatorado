package com.example.gubee.hexagon.api;

import java.util.List;

import com.example.gubee.hexagon.domain.Tecnologia;

public interface ServicoTecnologia {
	
	public Tecnologia save(Tecnologia tecnologia);
	public void update(int id, Tecnologia tecnologia);
	public void delete(int id);
	public Tecnologia getById(int id);
	public List<Tecnologia> getAll();

}

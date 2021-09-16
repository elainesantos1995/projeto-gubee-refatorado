package com.example.gubee.hexagon.spi;

import java.util.List;

import com.example.gubee.hexagon.domain.Tecnologia;

public interface TecnologiaRepository{

	Tecnologia save(Tecnologia tecnologia);
	void update(int id, Tecnologia tecnologia);
	void delete(int id);
	Tecnologia getById(int id);
	List<Tecnologia> getAll();
}

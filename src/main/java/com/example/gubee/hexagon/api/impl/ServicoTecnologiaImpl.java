package com.example.gubee.hexagon.api.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gubee.hexagon.api.ServicoTecnologia;
import com.example.gubee.hexagon.domain.Tecnologia;
import com.example.gubee.hexagon.spi.TecnologiaRepository;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//@Service
public class ServicoTecnologiaImpl implements ServicoTecnologia{
	
	private TecnologiaRepository repository;

	@Override
	public Tecnologia save(Tecnologia tecnologia) {
		return repository.save(tecnologia);
	}

	@Override
	public void update(int id, Tecnologia tecnologia) {
		repository.update(id, tecnologia);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);		
	}

	@Override
	public Tecnologia getById(int id) {
		return repository.getById(id);
	}

	@Override
	public List<Tecnologia> getAll() {
		return repository.getAll();
	}

}

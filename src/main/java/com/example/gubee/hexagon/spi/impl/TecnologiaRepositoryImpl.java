package com.example.gubee.hexagon.spi.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.gubee.hexagon.domain.Tecnologia;
import com.example.gubee.hexagon.domain.adapter.TecnologiaDB;
import com.example.gubee.hexagon.domain.adapter.TecnologiaRepositoryDB;
import com.example.gubee.hexagon.spi.TecnologiaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TecnologiaRepositoryImpl implements TecnologiaRepository{
		
	private final TecnologiaRepositoryDB repository;

	@Override
	public Tecnologia save(Tecnologia tecnologia) {
		TecnologiaDB tecnologiaDB = TecnologiaDB
				.builder()
				.nome(tecnologia.getNome())
				.build();
		
		tecnologiaDB = repository.save(tecnologiaDB);
		return tecnologiaDB.parseTecnologia();
	}

	@Override
	public void update(int id, Tecnologia tecnologia) {
		TecnologiaDB tecnologiaDB = TecnologiaDB
				.builder()
				.nome(tecnologia.getNome())
				.build();
		
		Optional<TecnologiaDB> tec = repository.findById(id);
		
		if(tec.isPresent()) {
			tecnologiaDB.setId(id);
			repository.save(tecnologiaDB);
		}else {
			ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Tecnologia não encontrada!");
			throw exception;
		}
		
	}

	@Override
	public void delete(int id) {
		this.repository.findById(id).map(tecnologia -> {
			this.repository.delete(tecnologia);
			return tecnologia;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

		
	}

	@Override
	public Tecnologia getById(int id) {
		return this.repository.findById(id).map(tecnologia -> {
			return tecnologia;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"))
				.parseTecnologia();

	}

	@Override
	public List<Tecnologia> getAll() {
		List<TecnologiaDB> tecnologiasDB = repository.findAll();
		List<Tecnologia> tecnologias = null;
		
		for(TecnologiaDB tecno: tecnologiasDB) {
			tecnologias.add(tecno.parseTecnologia());
		}
		
		return tecnologias;
	}

	
}

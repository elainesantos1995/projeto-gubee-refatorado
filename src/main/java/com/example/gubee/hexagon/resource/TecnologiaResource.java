package com.example.gubee.hexagon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gubee.hexagon.api.ServicoTecnologia;
import com.example.gubee.hexagon.domain.Tecnologia;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("gubee/tecnologias")
public class TecnologiaResource {
	
	@Autowired
	private ServicoTecnologia service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tecnologia save(@RequestBody Tecnologia tecnologia) {
		return service.save(tecnologia);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Tecnologia tecnologia) {
		this.service.update(id, tecnologia);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer id) {
		this.service.delete(id);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void getById(@PathVariable Integer id) {
		this.service.getById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Tecnologia> getAll() {
		return this.service.getAll();
	}
	
}

package com.catalogo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.dao.IGeneroRepository;
import com.catalogo.entities.Genero;

@Service
public class GeneroService implements IGeneroService {
	
	@Autowired
	private IGeneroRepository generoRepository;

	@Override
	public void save(Genero genero) {
		this.generoRepository.save(genero);
		
	}

	@Override
	public Genero findById(Long id) {
		return this.generoRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		this.generoRepository.deleteById(id);
	}

	@Override
	public List<Genero> findAll() {
		return (List<Genero>) this.generoRepository.findAll();
	}

}

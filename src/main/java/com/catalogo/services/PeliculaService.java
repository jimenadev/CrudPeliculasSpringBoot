package com.catalogo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.dao.IPeliculaRepository;
import com.catalogo.entities.Pelicula;

@Service
public class PeliculaService implements IPeliculaService {
	
	@Autowired
	private IPeliculaRepository repo;

	@Override
	public void save(Pelicula pelicula) {
		this.repo.save(pelicula);
	}

	@Override
	public Pelicula findById(Long id) {
		return this.repo.findById(id).orElse(null);
	}

	@Override
	public List<Pelicula> findAll() {
		return (List<Pelicula>) this.repo.findAll();
	}

	@Override
	public void delete(Long id) {
		this.repo.deleteById(null);
	}

}

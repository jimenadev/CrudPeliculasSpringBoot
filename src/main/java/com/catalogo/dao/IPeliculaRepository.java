package com.catalogo.dao;

import org.springframework.data.repository.CrudRepository;

import com.catalogo.entities.Pelicula;

public interface IPeliculaRepository extends CrudRepository<Pelicula, Long> {}

package com.catalogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.catalogo.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {}

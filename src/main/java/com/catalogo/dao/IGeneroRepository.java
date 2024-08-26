package com.catalogo.dao;

import org.springframework.data.repository.CrudRepository;

import com.catalogo.entities.Genero;

public interface IGeneroRepository extends CrudRepository<Genero, Long> {}

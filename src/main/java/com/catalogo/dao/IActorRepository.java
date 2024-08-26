package com.catalogo.dao;

import org.springframework.data.repository.CrudRepository;
import com.catalogo.entities.Actor;

public interface IActorRepository extends CrudRepository<Actor, Long> {}

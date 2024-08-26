package com.catalogo.services;

import java.util.List;
import com.catalogo.entities.Actor;

public interface IActorService {
	
	public List<Actor> findAll();
	public List<Actor> findAllById(List<Long> ids);

}

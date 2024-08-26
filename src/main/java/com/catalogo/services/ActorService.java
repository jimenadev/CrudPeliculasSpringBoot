package com.catalogo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.dao.IActorRepository;
import com.catalogo.entities.Actor;

@Service
public class ActorService implements IActorService {
	
	@Autowired
	private IActorRepository repo;

	@Override
	public List<Actor> findAll() {
		return (List<Actor>) this.repo.findAll();
	}

	@Override
	public List<Actor> findAllById(List<Long> ids) {
		return (List<Actor>) this.repo.findAllById(ids);
	}

}

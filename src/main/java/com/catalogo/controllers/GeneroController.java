package com.catalogo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.entities.Genero;
import com.catalogo.services.IGeneroService;

@RestController
public class GeneroController {
	
	private IGeneroService generoService;
	
	public GeneroController(IGeneroService generoService) {
		this.generoService = generoService;
	}

	@PostMapping("genero")
	public Long guardar(@RequestParam String nombre) {
		Genero genero = new Genero();
		genero.setNombre(nombre);
		this.generoService.save(genero);
		
		return genero.getId();
	}
	
	@GetMapping("genero/{id}")
	public String buscarPorId(@PathVariable(name = "id") Long id) {
		return this.generoService.findById(id).getNombre();
	}
	
}

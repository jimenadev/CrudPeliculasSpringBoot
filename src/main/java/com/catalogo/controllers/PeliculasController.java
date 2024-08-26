package com.catalogo.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.catalogo.entities.Pelicula;
import com.catalogo.services.IPeliculaService;

@Controller
public class PeliculasController {

	private IPeliculaService peliculaService;
	
	public PeliculasController(IPeliculaService peliculaService) {
		this.peliculaService = peliculaService;
	}
	
	@GetMapping("/pelicula")
	public String crear(Model model) {
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("titulo", "Nueva Película");
		return "pelicula";
	}
	
	@GetMapping("/pelicula/{id}")
	public String editar(@PathVariable(name="id") Long id) {
		Pelicula pelicula = new Pelicula();
		return "pelicula";
	}
	
	
	
}

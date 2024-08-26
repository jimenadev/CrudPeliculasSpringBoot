package com.catalogo.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.catalogo.entities.Actor;
import com.catalogo.entities.Pelicula;
import com.catalogo.services.IActorService;
import com.catalogo.services.IGeneroService;
import com.catalogo.services.IPeliculaService;

@Controller
public class PeliculasController {

	private IPeliculaService peliculaService;
	private IGeneroService generoService;
	private IActorService actorService;
	
	public PeliculasController(IPeliculaService peliculaService, IGeneroService generoService, IActorService actorService) {
		this.peliculaService = peliculaService;
		this.generoService = generoService;
		this.actorService = actorService;
	}
	
	@GetMapping("/pelicula")
	public String crear(Model model) {
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", this.generoService.findAll());
		model.addAttribute("actores", this.actorService.findAll());
		model.addAttribute("titulo", "Nueva Película");
		return "pelicula";
	}
	
	@GetMapping("/pelicula/{id}")
	public String editar(@PathVariable(name="id") Long id, Model model) {
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", this.generoService.findAll());
		model.addAttribute("actores", this.actorService.findAll());
		model.addAttribute("titulo", "Editar Película");
		return "pelicula";
	}
	
	@PostMapping("/pelicula")
	public String guardar(Pelicula pelicula, @ModelAttribute(name="ids") String ids) throws ParseException {
		System.out.println("------>"+ ids);
		List<Long> idsActores = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
		List<Actor> actores = this.actorService.findAllById(idsActores);
		pelicula.setProtagonistas(actores);
		this.peliculaService.save(pelicula);
		return "redirect:home";
	}
	
	@GetMapping({"/", "/home", "/index"})
	public String home(Pelicula pelicula) {
		return "home";
	}
	
	
	
}

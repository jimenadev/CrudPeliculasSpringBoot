package com.catalogo.controllers;


import java.io.IOException;
import java.text.ParseException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.catalogo.entities.Actor;
import com.catalogo.entities.Pelicula;
import com.catalogo.services.IActorService;
import com.catalogo.services.IArchivoService;
import com.catalogo.services.IGeneroService;
import com.catalogo.services.IPeliculaService;

import jakarta.validation.Valid;

@Controller
public class PeliculasController {

	private IPeliculaService peliculaService;
	private IGeneroService generoService;
	private IActorService actorService;
	private IArchivoService archivoService;
	
	public PeliculasController(IPeliculaService peliculaService, IGeneroService generoService, IActorService actorService, IArchivoService archivoService) {
		this.peliculaService = peliculaService;
		this.generoService = generoService;
		this.actorService = actorService;
		this.archivoService = archivoService;
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
		Pelicula pelicula = this.peliculaService.findById(id);
		String ids = "";
		
		for (Actor actor : pelicula.getProtagonistas()) {
			if("".equals(ids)) {
				ids = actor.getId().toString();
			}else {
				ids = ids +"," + actor.getId().toString();
			}
		}
		
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("ids",ids);
		model.addAttribute("generos", this.generoService.findAll());
		model.addAttribute("actores", this.actorService.findAll());
		model.addAttribute("titulo", "Editar Película");
		return "pelicula";
	}
	
	@PostMapping("/pelicula")
	public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name="ids") String ids, Model model, @RequestParam("archivo") MultipartFile imagen) throws ParseException {
		
		if(br.hasErrors()) {
			model.addAttribute("generos", this.generoService.findAll());
			model.addAttribute("actores", this.actorService.findAll());
			return "pelicula";
		}
		
		if(!imagen.isEmpty()) {
			String archivo = pelicula.getNombre() + this.getExtension(imagen.getOriginalFilename());
			pelicula.setImagen(archivo);
			try {
				this.archivoService.guardar(archivo, imagen.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			pelicula.setImagen("default.jpg");
		}
		
		if(ids != null && !"".equals(ids)) {
			List<Long> idsActores = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
			List<Actor> actores = this.actorService.findAllById(idsActores);
			pelicula.setProtagonistas(actores);
		}
		
		this.peliculaService.save(pelicula);
		
		return "redirect:home";
	}
	
	@GetMapping({"/", "/home", "/index"})
	public String home(Model model) {
		model.addAttribute("peliculas", this.peliculaService.findAll());
		//model.addAttribute("msj", "Catálogo actualizado a 2024");
		//model.addAttribute("tipoMsj", "success");
		return "home";
	}
	
	@GetMapping({"/listado"})
	public String listado(Model model) {
		model.addAttribute("titulo", "Listado de Películas");
		model.addAttribute("peliculas", this.peliculaService.findAll());
		return "listado";
	}
	
	private String getExtension(String archivo) {
		return archivo.substring(archivo.lastIndexOf("."));
	}
		
}

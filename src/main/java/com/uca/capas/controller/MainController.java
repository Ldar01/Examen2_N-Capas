package com.uca.capas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping("/index")
	public ModelAndView init() {
		
//		Libro libro = new Libro();
//	    
		ModelAndView mav = new ModelAndView();
//		mav.addObject("libro", libro);
//		List<Categoria> categorias = null;
//		
//		categorias = categoriaService.findAll();
//		mav.addObject("categoria", libro.getCodigoCategoria());
//		mav.addObject("categorias", categorias);
		mav.setViewName("index");
		
		return mav;		
	}
	
	@RequestMapping("/listadoLibros")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros =libroService.findAll(); 
		try {
			mav.addObject("libros", libros);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		mav.setViewName("listadoLibros");
		return mav;
	}
	
	@GetMapping("/agregarCategorias")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", new Categoria());
		mav.setViewName("agregarCategorias");
		return mav;
	}
	
	@GetMapping("/agregarLibros")
	public ModelAndView inicio2() {
		ModelAndView mav = new ModelAndView();
		Libro libro = new Libro();
		mav.addObject("libro", libro);
		List<Categoria> categorias = null;
		
		categorias = categoriaService.findAll();
		mav.addObject("categoria", libro.getCategoria());
		mav.addObject("categorias", categorias);
		mav.setViewName("agregarLibros");
		return mav;
	}
	
	//SAVE
	@RequestMapping("/save")
	public ModelAndView guardar(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("index");
		}
		else {
			libroService.save(libro);
			mav.setViewName("exitoLibro");
		}	
		return mav;
	}
	
	@RequestMapping("/saveCategoria")
	public ModelAndView guardar(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("index");
		}
		else {
			categoriaService.save(categoria);
			mav.setViewName("exitoCategoria");
		}	
		return mav;
	}
	
}

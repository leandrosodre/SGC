package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class PaginaInicialController {

	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping("/paginaInicial")
	public String paginaInicial() {
		return "/paginaInicial";	
	}
	
}

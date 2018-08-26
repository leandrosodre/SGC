package com.sgc.SGC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaInicialController {

	@RequestMapping("/paginaInicial")
	public String paginaInicial() {
		return "/paginaInicial";		
	}
}

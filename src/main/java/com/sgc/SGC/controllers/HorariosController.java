package com.sgc.SGC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HorariosController {
	
	@RequestMapping("/cadastrarHorarios")
	public String horarios() {
		return "horarios/formHorarios";
	}
	
	
	
}

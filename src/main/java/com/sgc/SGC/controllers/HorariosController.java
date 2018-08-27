package com.sgc.SGC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgc.SGC.models.Horarios;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.HorariosRepository;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class HorariosController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	HorariosRepository hr;
	
	@RequestMapping(value="/cadastrarHorarios", method=RequestMethod.GET)
	public String horarios(Model model) {	    
		List<Usuario> usuarios = ur.findAllMedicos();
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		return "horarios/formHorarios";
	}
	
	@RequestMapping(value="/cadastrarHorarios", method=RequestMethod.POST)
	public String cadastrarHorario(Horarios horarios) {
		hr.save(horarios);
		return "redirect:/horarios";
	}
	
	
}

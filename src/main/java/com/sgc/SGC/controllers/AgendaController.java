package com.sgc.SGC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class AgendaController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	AgendaRepository ar;
	
	@RequestMapping("/marcarConsulta")
	public String marcarConsulta(Model model) {
		List<Usuario> usuarios = ur.findAllMedicos();
		Iterable<Paciente> pacientes = pr.findAll();
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacientes);
		return "agendamento/FormMarcarConsulta";
	}
	
	@RequestMapping("/agenda")
	public ModelAndView carregarAgendas() {
		ModelAndView mv = new ModelAndView("agendamento/formAgenda");
		Iterable<Agenda> agenda = ar.findAll();
		mv.addObject("agendas", agenda);
		return mv;
	}
}

package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.validacoes.ValidarPaciente;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteRepository pr;
	
	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.GET)
	public String form() {
		return "pacientes/formPaciente";
	}
	
	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.POST)
	public String form(Paciente paciente, Model model) {
		ValidarPaciente validar = new ValidarPaciente(paciente);
		boolean pacienteValido = validar.pacienteValido();
		
		if ( !pacienteValido ) {
			model.addAttribute("erro", true);
			model.addAttribute("mensagem", validar.getMensagem());
			return "pacientes/formPaciente";
		}else {
			pr.save(paciente);
			return "redirect:/pacientes";
		}
	}
	
	@RequestMapping("/pacientes")
	public ModelAndView listaPacientes() {
		ModelAndView mv = new ModelAndView("pacientes/formListaPacientes");
		Iterable<Paciente> pacientes = pr.findAll();
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
	@RequestMapping("/pacientes/{idPaciente}")
	public ModelAndView editarPaciente(@PathVariable("idPaciente") long idPaciente) {
		Paciente paciente = pr.findByIdPaciente(idPaciente);		
		ModelAndView mv = new ModelAndView("pacientes/formEditarPaciente");
		mv.addObject("paciente", paciente);
		return mv;
	}
	
	@RequestMapping(value="/pacientes/{idPaciente}", method=RequestMethod.POST)
	public String atualizarPaciente(Paciente paciente) {
		pr.save(paciente);
		return "redirect:/pacientes";
	}
	
    @RequestMapping(value="/pacientes/delete/{idPaciente}")
    public String excluirPaciente(@RequestParam("idPaciente") long idPaciente) {
    	Paciente paciente = pr.findByIdPaciente(idPaciente);
        pr.delete(paciente);
        return "redirect:/pacientes";
    }
	
}

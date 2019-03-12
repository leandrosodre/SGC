package com.sgc.SGC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/horarios")
	public ModelAndView listHorarios() {
		ModelAndView mv = new ModelAndView("horarios/formListaHorarios");
		Iterable<Horarios> horarios = hr.findAll();
		mv.addObject("horarios", horarios);
		return mv;
	}
	
	@RequestMapping("/horarios/{idHorarios}")
	public ModelAndView editarHorarios(@PathVariable("idHorarios") long idHorarios) {
		Horarios horarios = hr.findByIdHorarios(idHorarios);		
		List<Usuario> usuarios = ur.findAllMedicos();
		ModelAndView mv = new ModelAndView("horarios/formEditarHorarios");
		mv.addObject("horarios", horarios);
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value="/horarios/{idHorarios}", method=RequestMethod.POST)
	public String atualizarHorarios(Horarios horarios) {
		hr.save(horarios);
		return "redirect:/horarios";
	}
	
    @RequestMapping(value="/horarios/delete/{idHorarios}")
    public String excluirHorarios(@RequestParam("idHorarios") long idHorarios) {
    	Horarios horarios = hr.findByIdHorarios(idHorarios);
        hr.delete(horarios);
        return "redirect:/horarios";
    }
    
    @RequestMapping("/buscarHorarios")
	public ModelAndView buscarHorarios(@RequestParam("nomeMedico") String nomeMedico,
										@RequestParam("diaSemana") int diaSemana) {
    	
    	ModelAndView mv = new ModelAndView("horarios/formListaHorarios");   
    	
    	Iterable<Horarios> horarios;
    	if (nomeMedico != "") {
    		horarios = hr.findByNomeUsuario(nomeMedico);
    	} else if (diaSemana != 0) {
    		horarios = hr.findByDiaSemana(diaSemana);
    	} else {
    		horarios = hr.findAll();
    	}
    	mv.addObject("horarios", horarios);
		return mv;
	}
	
	
}

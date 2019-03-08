package com.sgc.SGC.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Exame;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.TipoExame;
import com.sgc.SGC.repository.ExameRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.TipoExameRepository;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class ExameController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	ExameRepository er;
	
	@Autowired
	TipoExameRepository ter;
	
	@SuppressWarnings("null")
	@RequestMapping("/exame")
	public ModelAndView carregarExames() {
		ModelAndView mv = new ModelAndView("exames/formListaPacientesExames");
		List<Long> idsPacientes = er.findAllPacientesComExame();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		for (int i = 0; i < idsPacientes.size(); i++) {
			Long id = idsPacientes.get(i);
			Paciente paciente = new Paciente();
			paciente = pr.findByIdPaciente(id);
			pacientes.add(paciente);
		}
		
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
	@RequestMapping("/exame/{idPaciente}")
	public ModelAndView consultaExames(@PathVariable("idPaciente") long idPaciente) {
		List<Exame> exames = er.findAllExamesDoPaciente(idPaciente);		
		ModelAndView mv = new ModelAndView("exames/formListaExamesPaciente");
		mv.addObject("exames", exames);
		return mv;
	}
	
	@RequestMapping("/exame/{idExame}")
	public ModelAndView detalhaExame(@PathVariable("idExame") long idExame) {
		Exame exame = er.findByIdExame(idExame);		
		ModelAndView mv = new ModelAndView("exames/formDetalhesExame");
		mv.addObject("exame", exame);
		return mv;
	}
	
	@RequestMapping(value="/solicitarExame", method=RequestMethod.GET)
	public String solicitarExame(Model model) {
		Iterable<TipoExame> tipoExames = ter.findAll();
		Iterable<Paciente> pacientes = pr.findAll();
		
		model.addAttribute("tipoExame", new TipoExame());
		model.addAttribute("tipoExames", tipoExames);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacientes);
		
		return "exames/FormExame";
	}
	
	
	@RequestMapping(value="/solicitarExame", method=RequestMethod.POST)
	public String solicitarExame(Exame exame) {
		System.out.println("TESTE");
		long idTipoExame = exame.getTipoExame().getIdTipoExame();
		TipoExame tipoExame = ter.findByIdTipoExame(idTipoExame);
		exame.setTipoExame(tipoExame);
		er.save(exame);
		return "redirect:/exame";
	}
}

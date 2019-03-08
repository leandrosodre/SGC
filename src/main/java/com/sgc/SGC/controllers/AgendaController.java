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

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;

@Controller
public class AgendaController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	AgendaRepository ar;
	
	@RequestMapping(value="/marcarConsulta", method=RequestMethod.GET)
	public String marcarConsulta(Model model) {
		List<Usuario> usuarios 			= ur.findAllMedicos();
		Iterable<Paciente> pacientes 	= pr.findAll();
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacientes);
		return "agendamento/FormMarcarConsulta";
	}
	
	@RequestMapping(value="/marcarConsulta", method=RequestMethod.POST)
	public String marcarConsulta(Agenda agenda) {
		String nomeUsuario 		= new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		long idUsuarioMedico 	= agenda.getUsuarioMedico().getIdUsuario();
		Usuario usuarioMarcador = ur.findByLogin(nomeUsuario);
		Usuario usuarioMedico 	= ur.findByIdUsuario(idUsuarioMedico);
		
		agenda.setUsuarioMarcador(usuarioMarcador);
		agenda.setUsuarioMedico(usuarioMedico);
		
		ar.save(agenda);
		return "redirect:/agenda";
	}
	
	@RequestMapping("/agenda")
	public ModelAndView carregarAgendas() {
		ModelAndView mv = new ModelAndView("agendamento/formAgenda");
		Iterable<Agenda> agenda = ar.findAll();
		mv.addObject("agendas", agenda);
		return mv;
	}
	
	@RequestMapping(value="/agenda/{idAgenda}", method=RequestMethod.POST)
	public String atualizarAgenda(Agenda agenda) {
		String nomeUsuario 		= new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioMarcador = ur.findByLogin(nomeUsuario);
		agenda.setUsuarioMarcador(usuarioMarcador);
		ar.save(agenda);
		return "redirect:/agenda";
	}
	
	
	@RequestMapping("/agenda/{idAgenda}")
	public ModelAndView editarAgenda(@PathVariable("idAgenda") long idAgenda) {
		Agenda agenda = ar.findByIdAgenda(idAgenda);		
		List<Usuario> usuarios = ur.findAllMedicos();
		Iterable<Paciente> pacientes 	= pr.findAll();
		ModelAndView mv = new ModelAndView("agendamento/formEditarAgenda");
		mv.addObject("agenda", agenda);
		mv.addObject("usuarios", usuarios);
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
    @RequestMapping(value="/agenda/delete/{idAgenda}")
    public String excluirAgenda(@RequestParam("idAgenda") long idAgenda) {
    	Agenda agenda = ar.findByIdAgenda(idAgenda);
        ar.delete(agenda);
        return "redirect:/agenda";
    }
}

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.validacoes.ValidarPaciente;

@Controller
public class PacienteController {
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	private PacienteRepository pr;
	
	@Autowired
	private AgendaRepository ar;
	
	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.GET)
	public String form(Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		return "pacientes/formPaciente";
	}
	
	@RequestMapping(value="/cadastrarPaciente", method=RequestMethod.POST)
	public String form(Paciente paciente, Model model) {
		ValidarPaciente validar = new ValidarPaciente(paciente);
		boolean pacienteValido = validar.pacienteValido();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		
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
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
	@RequestMapping("/pacientes/{idPaciente}")
	public ModelAndView editarPaciente(@PathVariable("idPaciente") long idPaciente) {
		Paciente paciente = pr.findByIdPaciente(idPaciente);		
		ModelAndView mv = new ModelAndView("pacientes/formEditarPaciente");
		mv.addObject("paciente", paciente);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		return mv;
	}
	
	@RequestMapping(value="/pacientes/{idPaciente}", method=RequestMethod.POST)
	public String atualizarPaciente(Paciente paciente) {
		pr.save(paciente);
		return "redirect:/pacientes";
	}
	
    @RequestMapping(value="/pacientes/delete/{idPaciente}")
    public String excluirPaciente(@PathVariable("idPaciente") long idPaciente, RedirectAttributes redirectAttributes) {
    	Paciente paciente = pr.findByIdPaciente(idPaciente);
    	List<Agenda> agenda = ar.findAllAgendasDoPaciente(idPaciente);
    	if (agenda.isEmpty()) {
	        pr.delete(paciente);
	        redirectAttributes.addFlashAttribute("message", "Registro Excluido com sucesso.");
	        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	        return "redirect:/pacientes";
    	}
    	redirectAttributes.addFlashAttribute("message", "Falha ao Excluir Registro (Existem agendas para o paciente)");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    	return "redirect:/pacientes";
    }
	
    @RequestMapping("/buscarPacientes")
	public ModelAndView buscarPacientes(@RequestParam("cpf") String cpf,
										@RequestParam("nome") String nomePaciente) {
    	ModelAndView mv = new ModelAndView("pacientes/formListaPacientes");   	
    	
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
    	
		Iterable<Paciente> pacientes;
    	if (cpf != "") {
    		pacientes = pr.findByCPFLike(cpf);
    	} else if (nomePaciente != "") {
    		pacientes = pr.findByName(nomePaciente);
    	} else {
    		pacientes = pr.findAll();
    	}

    	mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
    	mv.addObject("pacientes", pacientes);
		return mv;
	}
	
    
}

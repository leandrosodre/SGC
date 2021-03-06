package com.sgc.SGC.controllers;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.sgc.SGC.models.DisponibilidadeHorario;
import com.sgc.SGC.models.Horarios;
import com.sgc.SGC.models.Mensagem;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.DisponibilidadeHorarioRepository;
import com.sgc.SGC.repository.HorariosRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.validacoes.ValidarAgenda;

@Controller
public class AgendaController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	AgendaRepository ar;
	
	@Autowired
	HorariosRepository hr;
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	DisponibilidadeHorarioRepository dhr;
	
	@RequestMapping(value="/marcarConsulta", method=RequestMethod.GET)
	public String marcarConsulta(Model model) {
		List<Usuario> usuarios 			= ur.findAllMedicos();
		Iterable<Paciente> pacientes 	= pr.findAll();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<DisponibilidadeHorario> disp = dhr.findAllDisponivel();
		model.addAttribute("DisponibilidadeHorario", disp);
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacientes);
		return "agendamento/formMarcarConsulta";
	}
	
	@RequestMapping(value="/marcarConsulta", method=RequestMethod.POST)
	public String marcarConsulta(Agenda agenda, Model model) {
		long idUsuarioMedico 	= agenda.getUsuarioMedico().getIdUsuario();
		Usuario usuarioMedico 	= ur.findByIdUsuario(idUsuarioMedico);
		List<Horarios> horarios = hr.findByNomeUsuario(usuarioMedico.getNomeUsuario().toString());
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		
		agenda.setUsuarioMedico(usuarioMedico);
		Paciente paciente = pr.findByIdPaciente(agenda.getPaciente().getIdPaciente());
		Iterable<DisponibilidadeHorario> disp = dhr.findAllDisponivel();
		model.addAttribute("DisponibilidadeHorario", disp);
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		List<DisponibilidadeHorario> horariosDisponiveis = dhr.findAllDisponivelDoMedico(usuarioMedico.getIdUsuario()); 
		ValidarAgenda validar = new ValidarAgenda(agenda, horarios, horariosDisponiveis);
		boolean agendaValida = validar.horariosValido();
		
		if ( !agendaValida ) {
			List<Usuario> usuarios 			= ur.findAllMedicos();
			Iterable<Paciente> pacientes 	= pr.findAll();
			
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("paciente", new Paciente());
			model.addAttribute("pacientes", pacientes);
			
			model.addAttribute("erro", true);
			model.addAttribute("mensagem", validar.getMensagem());
			
			return "agendamento/formMarcarConsulta";
		}else {
			Mensagem mensagem = new Mensagem();
	    	mensagem.setUsuarioRemet(usuarioLogado);
	    	mensagem.setUsuarioDest(usuarioMedico);
	    	Calendar c = Calendar.getInstance();
	    	c.setTime(agenda.getDataPrevista());
	    	mensagem.setTextoMensagem("Consulta marcada para o paciente "+ paciente.getNomeCompleto() +" no dia "+ String.format("%02d",c.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d", c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
	    	mensagem.setLida('N');
	    	mer.save(mensagem);    
	    	
	    	DisponibilidadeHorario disponivel = dhr.findRegistro(c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.HOUR), c.get(Calendar.MINUTE), usuarioMedico.getIdUsuario());
	    	disponivel.setDisponivel('N');
	    	
	    	dhr.save(disponivel);
			ar.save(agenda);
			return "redirect:/agenda";
		}
	}
	
	@RequestMapping("/agenda")
	public ModelAndView carregarAgendas() {
		ModelAndView mv = new ModelAndView("agendamento/formAgenda");
		List<Agenda> agenda = ar.findAllAgendasEmAberto();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("agendas", agenda);
		return mv;
	}
	
	@RequestMapping(value="/agenda/{idAgenda}", method=RequestMethod.POST)
	public String atualizarAgenda(Agenda agenda) {
		ar.save(agenda);
		return "redirect:/agenda";
	}
	
	
	@RequestMapping("/agenda/{idAgenda}")
	public ModelAndView editarAgenda(@PathVariable("idAgenda") long idAgenda) {
		Agenda agenda = ar.findByIdAgenda(idAgenda);		
		List<Usuario> usuarios = ur.findAllMedicos();
		Iterable<Paciente> pacientes 	= pr.findAll();
		ModelAndView mv = new ModelAndView("agendamento/formEditarAgenda");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("agenda", agenda);
		mv.addObject("usuarios", usuarios);
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
    @RequestMapping(value="/agenda/delete/{idAgenda}")
    public String excluirAgenda(@RequestParam("idAgenda") long idAgenda) {
    	Agenda agenda = ar.findByIdAgenda(idAgenda);
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(agenda.getDataPrevista());
    	DisponibilidadeHorario disponivel = dhr.findRegistro(c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.HOUR), c.get(Calendar.MINUTE), agenda.getUsuarioMedico().getIdUsuario());
    	disponivel.setDisponivel('S');
    	dhr.save(disponivel);
        ar.delete(agenda);
        return "redirect:/agenda";
    }
    
    @RequestMapping("/buscarAgendas")
	public ModelAndView buscarAgendas(@RequestParam("nomePaciente") String nomePaciente,
									  @RequestParam("nomeMedico") String nomeMedico) {
    	
    	ModelAndView mv = new ModelAndView("agendamento/formAgenda");   
    	
    	
    	Iterable<Paciente> pacientes;
    	Iterable<Usuario> medicos;
    	List<Agenda> agendas = new ArrayList<Agenda>();
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
    	Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
    	int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
    	mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
    	if (nomePaciente != "") {
    		pacientes = pr.findByName(nomePaciente);
    		for (Paciente paciente : pacientes) {
    			List<Agenda> agendasPaciente = ar.findAllAgendasDoPaciente(paciente.idPaciente);
    			agendas.addAll(agendasPaciente);
    		}
    	} else if (nomeMedico != "") {
    		medicos = ur.findAllByName(nomeMedico);
    		for (Usuario medico : medicos) {
    			List<Agenda> agendasMedico = ar.findAllAgendasDoMedico(medico.getIdUsuario());
    			agendas.addAll(agendasMedico);
    		}
    	} else {
    		Iterable<Agenda> agenda = ar.findAll();
    		mv.addObject("agendas", agenda);
    	}
    	
    	if (agendas.size() > 0) {
    		mv.addObject("agendas", agendas);
    	}
    	
		return mv;
	}
}

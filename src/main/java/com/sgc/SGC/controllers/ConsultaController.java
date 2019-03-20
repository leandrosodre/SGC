package com.sgc.SGC.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.Consulta;
import com.sgc.SGC.models.Medicamento;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.Posologia;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.ConsultaRepository;
import com.sgc.SGC.repository.MedicamentoRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.PosologiaRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.utilidades.ImprimirReceita;

@Controller
public class ConsultaController {
	
	@Autowired
	AgendaRepository ar;
	
	@Autowired
	MedicamentoRepository mr;
	
	@Autowired
	PosologiaRepository por;
	
	@Autowired
	ConsultaRepository cr;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	MensagemRepository mer;
	
	@Resource
    private ImprimirReceita imprimirReceita;
	
	@RequestMapping("/consulta")
	public ModelAndView listarConsultas() {
		ModelAndView mv = new ModelAndView("consulta/formListaConsulta");
		List<Usuario> 		usuarios 	= ur.findAllMedicos();
		Iterable<Paciente> 	pacientes 	= pr.findAll();
		List<Agenda> 		agendas 	= ar.findAllAgendasEmAberto();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("usuario", new Usuario());
		mv.addObject("usuarios", usuarios);
		mv.addObject("paciente", new Paciente());
		mv.addObject("pacientes", pacientes);
		mv.addObject("agendas", agendas);
		return mv;
	}
	
	@RequestMapping("/listaConsulta")
	public ModelAndView listarConsultasRealizadas() {
		ModelAndView mv = new ModelAndView("consulta/formListarConsultasRealizadas");
		List<Usuario> 		usuarios 	= ur.findAllMedicos();
		Iterable<Paciente> 	pacientes 	= pr.findAll();
		List<Agenda> 		agendas 	= ar.findAllAgendasRealizadas();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("usuario", new Usuario());
		mv.addObject("usuarios", usuarios);
		mv.addObject("paciente", new Paciente());
		mv.addObject("pacientes", pacientes);
		mv.addObject("agendas", agendas);
		return mv;
	}
	
	@RequestMapping(value="/realizarConsulta/{idAgenda}", method=RequestMethod.GET)
	public ModelAndView realizarConsulta(@PathVariable("idAgenda") long idAgenda) {
		ModelAndView mv = new ModelAndView("consulta/formRealizarConsulta");
		List<Posologia> posologias = por.findAllPosologiasDaAgenda(idAgenda);
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("posologias", posologias);
		mv.addObject("agenda", agenda);
		return mv;
	}
	
	@RequestMapping(value="/detalharConsulta/{idAgenda}", method=RequestMethod.GET)
	public ModelAndView detalharConsulta(@PathVariable("idAgenda") long idAgenda) {
		ModelAndView mv = new ModelAndView("consulta/formDetalhesConsulta");
		List<Posologia> posologias = por.findAllPosologiasDaAgenda(idAgenda);
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		Consulta consulta = cr.findByIdConsulta(agenda.getConsulta().getIdConsulta());
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("consulta",consulta);
		mv.addObject("posologias", posologias);
		mv.addObject("agenda", agenda);
		return mv;
	}
	
	
	
	@RequestMapping(value="/realizarConsulta/{idAgenda}", method=RequestMethod.POST)
	public String salvarConsulta(Consulta consulta, @PathVariable("idAgenda") long idAgenda) {
		cr.save(consulta);
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		agenda.setResultado("F");
		agenda.setConsulta(consulta);
		ar.save(agenda);
		return "redirect:/consulta";
	}
	
	
	@RequestMapping(value="/posologias/{idPosologia}", method=RequestMethod.GET)
	public ModelAndView editarPosologia(@PathVariable("idPosologia") long idPosologia) {
		ModelAndView mv = new ModelAndView("consulta/formEditarSolicitacaoMedicamento");
		Posologia posologia = por.findByIdPosologia(idPosologia);
		Iterable<Medicamento> medicamentos = mr.findAll();
		Agenda agenda = ar.findByIdAgenda(posologia.getAgenda().getIdAgenda());
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("agenda", agenda);
		mv.addObject("medicamento", new Medicamento());
		mv.addObject("medicamentos", medicamentos);
		mv.addObject("posologia", posologia);
		
		return mv;
	}
	
	@RequestMapping(value="/posologias/{idPosologia}", method=RequestMethod.POST)
	public String atualizarSolicitacao(Posologia posologia) {
		Agenda agenda = por.findAgenda(posologia.idPosologia);
		posologia.setAgenda(agenda);
		por.save(posologia);
		return "redirect:/realizarConsulta/" + String.valueOf(posologia.getAgenda().getIdAgenda());
	}
	
	@RequestMapping(value="/realizarConsulta/solicitarMedicamento/{idAgenda}", method=RequestMethod.GET)
	public ModelAndView solicitarMedicamento(@PathVariable("idAgenda") long idAgenda) {
		ModelAndView mv = new ModelAndView("consulta/formSolicitarMedicamento");
		Iterable<Medicamento> medicamentos = mr.findAllMedicamentosAtivos();
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("agenda", agenda);
		mv.addObject("medicamento", new Medicamento());
		mv.addObject("medicamentos", medicamentos);
		
		return mv;
	}
	
	@RequestMapping(value="/realizarConsulta/solicitarMedicamento/{idAgenda}", method=RequestMethod.POST)
	public String cadastrarMedicamento(Posologia posologia, @PathVariable("idAgenda") long idAgenda) {
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		posologia.setAgenda(agenda);
		por.save(posologia);
		return "redirect:/realizarConsulta/" + String.valueOf(idAgenda);
	}
	
	 @RequestMapping(value="/posologia/delete/{idPosologia}")
    public String excluirPosologia(@RequestParam("idPosologia") long idPosologia) {
    	Posologia posologia = por.findByIdPosologia(idPosologia);
    	long idAgenda = posologia.getAgenda().getIdAgenda();
        por.delete(posologia);
        return "redirect:/realizarConsulta/" + String.valueOf(idAgenda);
    }
	 
    @RequestMapping("/buscarConsultas")
	public ModelAndView buscarConsultas(@RequestParam("idMedico") long idMedico,
										@RequestParam("idPaciente") long idPaciente) {
    	ModelAndView mv = new ModelAndView("consulta/formListaConsulta");   	
		List<Usuario> 		usuarios 	= ur.findAllMedicos();
		Iterable<Paciente> 	pacientes 	= pr.findAll();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("usuario", new Usuario());
		mv.addObject("usuarios", usuarios);
		mv.addObject("paciente", new Paciente());
		mv.addObject("pacientes", pacientes);
    	List<Agenda> agendas;
    	if (idMedico != 0) {
    		agendas = ar.findAllAgendasEmAbertoDoMedico(idMedico);
    	} else if (idPaciente != 0) {
    		agendas = ar.findAllAgendasEmAbertoDoPaciente(idPaciente);
    	} else {
    		agendas = ar.findAllAgendasEmAberto();
    	}
    	mv.addObject("agendas", agendas);
		return mv;
	}
    
    @RequestMapping("/buscarConsultasRealizadas")
	public ModelAndView buscarConsultasRealizadas(@RequestParam("idMedico") long idMedico,
													@RequestParam("idPaciente") long idPaciente) {
    	ModelAndView mv = new ModelAndView("consulta/formListarConsultasRealizadas");   	
		List<Usuario> 		usuarios 	= ur.findAllMedicos();
		Iterable<Paciente> 	pacientes 	= pr.findAll();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("usuario", new Usuario());
		mv.addObject("usuarios", usuarios);
		mv.addObject("paciente", new Paciente());
		mv.addObject("pacientes", pacientes);
    	List<Agenda> agendas;
    	if (idMedico != 0) {
    		agendas = ar.findAllAgendasRealizadasDoMedico(idMedico);
    	} else if (idPaciente != 0) {
    		agendas = ar.findAllAgendasRealizadasDoPaciente(idPaciente);
    	} else {
    		agendas = ar.findAllAgendasRealizadas();
    	}
    	mv.addObject("agendas", agendas);
		return mv;
	}
    
    @RequestMapping(value="/imprimirReceita/{idAgenda}", method=RequestMethod.GET)
	public void imprimirReceita(@PathVariable("idAgenda") long idAgenda) throws Exception {
		List<Posologia> posologias = por.findAllPosologiasDaAgenda(idAgenda);
		Agenda agenda = ar.findByIdAgenda(idAgenda);
		Paciente paciente = pr.findByIdPaciente(agenda.getPaciente().getIdPaciente());
		
		imprimirReceita.generateInvoiceFor(paciente, posologias);
		
		
	}
	
	
}

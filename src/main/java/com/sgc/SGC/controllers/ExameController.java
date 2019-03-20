
package com.sgc.SGC.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Exame;
import com.sgc.SGC.models.Mensagem;
import com.sgc.SGC.models.Paciente;
import com.sgc.SGC.models.TipoExame;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.ExameRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.TipoExameRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;

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
	
	@Autowired
	MensagemRepository mer;
	
	@SuppressWarnings("null")
	@RequestMapping("/exame")
	public ModelAndView carregarExames() {
		ModelAndView mv = new ModelAndView("exames/formListaPacientesExames");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		List<Long> idsPacientes = er.findAllPacientesComExame();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		for (int i = 0; i < idsPacientes.size(); i++) {
			Long id = idsPacientes.get(i);
			Paciente paciente = new Paciente();
			paciente = pr.findByIdPaciente(id);
			pacientes.add(paciente);
		}
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("pacientes", pacientes);
		return mv;
	}
	
	@RequestMapping("/exame/{idPaciente}")
	public ModelAndView consultaExames(@PathVariable("idPaciente") long idPaciente) {
		List<Exame> exames = er.findAllExamesDoPaciente(idPaciente);		
		ModelAndView mv = new ModelAndView("exames/formListaExamesPaciente");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("exames", exames);
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		return mv;
	}
	
	@RequestMapping("/exame/detalhaExame/{idExame}")
	public ModelAndView detalhaExame(@PathVariable("idExame") long idExame) {
		Exame exame = er.findByIdExame(idExame);		
		ModelAndView mv = new ModelAndView("exames/formDetalhesExame");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<TipoExame> tipoExames = ter.findAll();
		Iterable<Paciente> pacientes = pr.findAll();
		mv.addObject("tipoExames", tipoExames);
		mv.addObject("pacientes", pacientes);
		mv.addObject("exame", exame);
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		return mv;
	}
	
	@RequestMapping(value="/solicitarExame", method=RequestMethod.GET)
	public String solicitarExame(Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<TipoExame> tipoExames = ter.findAll();
		Iterable<Paciente> pacientes = pr.findAll();
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		model.addAttribute("tipoExame", new TipoExame());
		model.addAttribute("tipoExames", tipoExames);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacientes);
		
		return "exames/FormExame";
	}
	
	
	@RequestMapping(value="/solicitarExame", method=RequestMethod.POST)
	public String solicitarExame(Exame exame) {
		long idTipoExame = exame.getTipoExame().getIdTipoExame();
		TipoExame tipoExame = ter.findByIdTipoExame(idTipoExame);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		exame.setTipoExame(tipoExame);
		exame.setUsuarioSolicitante(usuarioLogado);
		exame.setResultado(null);
		er.save(exame);
		return "redirect:/exame";
	}
	
    @RequestMapping("/buscarExames")
	public ModelAndView buscarExames(@RequestParam("nome") String nome) {
    	
    	ModelAndView mv = new ModelAndView("exames/formListaPacientesExames");   	
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
    	Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
    	int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
    	List<Long> idsPacientes;
    	List<Paciente> pacientes;
    	
    	if (nome != "") {
    		idsPacientes = er.findLikePacientesComExame(nome);
    		pacientes = new ArrayList<Paciente>();
    		for (int i = 0; i < idsPacientes.size(); i++) {
    			Long id = idsPacientes.get(i);
    			Paciente paciente = new Paciente();
    			paciente = pr.findByIdPaciente(id);
    			pacientes.add(paciente);
    		}
    	} else {
    		idsPacientes = er.findAllPacientesComExame();
    		pacientes = new ArrayList<Paciente>();
    		for (int i = 0; i < idsPacientes.size(); i++) {
    			Long id = idsPacientes.get(i);
    			Paciente paciente = new Paciente();
    			paciente = pr.findByIdPaciente(id);
    			pacientes.add(paciente);
    		}
    	}
    	mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
    	mv.addObject("pacientes", pacientes);
		return mv;
	}
    
    @RequestMapping("/buscarTodosExames")
	public ModelAndView buscarTodosExames(Exame exame) {
		ModelAndView mv = new ModelAndView("realizarExame/formCarregarExames");
		List<Exame> exames = er.findAllExamesPendentes();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("exames", exames);
		return mv;
	}
    
    @RequestMapping(value="/carregarExame/{idExame}", method=RequestMethod.GET)
	public ModelAndView carregarExame(@PathVariable("idExame") long idExame) {
    	ModelAndView mv = new ModelAndView("realizarExame/formRealizarExame");
		Exame exame = er.findByIdExame(idExame);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<TipoExame> tipoExames = ter.findAll();
		Iterable<Paciente> pacientes = pr.findAll();
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("tipoExames", tipoExames);
		mv.addObject("pacientes", pacientes);
		mv.addObject("exame", exame);
		mv.addObject("exame", exame);
		return mv;
	}
    
    @RequestMapping(value="/carregarExame/{idExame}", method=RequestMethod.POST)
	public String salvarRealizarExame(@PathVariable("idExame") long idExame, Exame exame) {
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
    	Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		Exame exameValores = er.findByIdExame(idExame);
    	exameValores.setResultado(exame.getResultado());
    	Mensagem mensagem = new Mensagem();
    	mensagem.setUsuarioRemet(usuarioLogado);
    	mensagem.setUsuarioDest(exameValores.getUsuarioSolicitante());
    	mensagem.setTextoMensagem("O Exame numero " + idExame + " do paciente (" + exameValores.getPaciente().getNomeCompleto() + ") foi concluido e já pode ser verificado o resultado.");
    	mensagem.setLida('N');
    	mer.save(mensagem);    	
    	er.save(exameValores);
		return "redirect:/buscarTodosExames";
	}
    
    @RequestMapping("/editar/{idExame}")
	public ModelAndView editarExame(@PathVariable("idExame") long idExame) {
		Exame exame = er.findByIdExame(idExame);		
		ModelAndView mv = new ModelAndView("exames/formEditarExame");
		Iterable<TipoExame> tipoExames = ter.findAll();
		Iterable<Paciente> pacientes = pr.findAll();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("tipoExame", new TipoExame());
		mv.addObject("tipoExames", tipoExames);
		mv.addObject("paciente", new Paciente());
		mv.addObject("pacientes", pacientes);
		mv.addObject("exame", exame);
		return mv;
	}
	
	@RequestMapping(value="/editar/{idExame}", method=RequestMethod.POST)
	public String atualizarExame(Exame exame) {
		long idTipoExame = exame.getTipoExame().getIdTipoExame();
		TipoExame tipoExame = ter.findByIdTipoExame(idTipoExame);
		exame.setTipoExame(tipoExame);
		exame.setResultado(null);
		er.save(exame);
		return "redirect:/exame";
	}
    
}

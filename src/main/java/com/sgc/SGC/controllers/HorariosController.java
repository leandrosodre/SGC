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

import com.sgc.SGC.models.DisponibilidadeHorario;
import com.sgc.SGC.models.Horarios;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.DisponibilidadeHorarioRepository;
import com.sgc.SGC.repository.HorariosRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.validacoes.ValidarHorarios;

@Controller
public class HorariosController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	HorariosRepository hr;
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	DisponibilidadeHorarioRepository dhr;
	
	@RequestMapping(value="/cadastrarHorarios", method=RequestMethod.GET)
	public String horarios(Model model) {	    
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		List<Usuario> usuarios = ur.findAllMedicos();
		
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		return "horarios/formHorarios";
	}
	
	@RequestMapping(value="/cadastrarHorarios", method=RequestMethod.POST)
	public String cadastrarHorario(Horarios horarios, Model model) {
		ValidarHorarios validar = new ValidarHorarios(horarios);
		boolean horariosValido = validar.horariosValido();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		
		cadastrarDisponibilidade(horarios);
		
		
		if ( !horariosValido ) {
			List<Usuario> usuarios = ur.findAllMedicos();
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("erro", true);
			model.addAttribute("mensagem", validar.getMensagem());
			return "horarios/formHorarios";
		}else {
			hr.save(horarios);
			return "redirect:/horarios";
		}
	}
	
	@RequestMapping("/horarios")
	public ModelAndView listHorarios() {
		ModelAndView mv = new ModelAndView("horarios/formListaHorarios");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<Horarios> horarios = hr.findAll();
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("horarios", horarios);
		return mv;
	}
	
	@RequestMapping("/horarios/{idHorarios}")
	public ModelAndView editarHorarios(@PathVariable("idHorarios") long idHorarios) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Horarios horarios = hr.findByIdHorarios(idHorarios);
		List<Usuario> usuarios = ur.findAllMedicos();
		ModelAndView mv = new ModelAndView("horarios/formEditarHorarios");
		mv.addObject("horarios", horarios);
		mv.addObject("usuarios", usuarios);
		mv.addObject("quantidadeNaolidas",quantidadeNaolidas);
		return mv;
	}
	
	@RequestMapping(value="/horarios/{idHorarios}", method=RequestMethod.POST)
	public String atualizarHorarios(Horarios horarios) {
		excluirTodasDisponibilidades(horarios.getUsuario().getIdUsuario());
		cadastrarDisponibilidade(horarios);
		hr.save(horarios);
		return "redirect:/horarios";
	}
	
    @RequestMapping(value="/horarios/delete/{idHorarios}")
    public String excluirHorarios(@RequestParam("idHorarios") long idHorarios) {
    	Horarios horarios = hr.findByIdHorarios(idHorarios);
    	excluirDisponibilidade(horarios);    	
        hr.delete(horarios);
        return "redirect:/horarios";
    }
    
    @RequestMapping("/buscarHorarios")
	public ModelAndView buscarHorarios(@RequestParam("nomeMedico") String nomeMedico,
										@RequestParam("diaSemana") int diaSemana) {
    	
    	ModelAndView mv = new ModelAndView("horarios/formListaHorarios");   
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
    	List<Horarios> horarios;
    	if (nomeMedico != "") {
    		horarios = hr.findByNomeUsuario(nomeMedico);
    	} else if (diaSemana != 0) {
    		horarios = hr.findByDiaSemana(diaSemana);
    	} else {
    		horarios = hr.findAll();
    	}
    	mv.addObject("quantidadeNaolidas",quantidadeNaolidas);
    	mv.addObject("horarios", horarios);
		return mv;
	}
	
	private void cadastrarDisponibilidade(Horarios horarios) {
		for (int h=horarios.getHoraInicio(); h<horarios.getHoraFim();h++) {
			if (h < 24) {
				for (int m=horarios.getMinutoInicio(); m<60;m = m+30) {
					if (m < 60) {
						DisponibilidadeHorario disp = new DisponibilidadeHorario();
						disp.setDiaSemana(horarios.getDiaSemana());
						disp.setHora(h);
						disp.setMinuto(m);
						disp.setUsuario(horarios.getUsuario());
						disp.setDisponivel('S');
						dhr.save(disp);
					}
				}
			}
		}
	}
	
	private void excluirDisponibilidade(Horarios horarios) {
		for (int h=horarios.getHoraInicio(); h<horarios.getHoraFim();h++) {
			if (h < 24) {
				for (int m=horarios.getMinutoInicio(); m<60;m = m+30) {
					if (m < 60) {
						DisponibilidadeHorario disp = dhr.findRegistro(horarios.getDiaSemana(), h, m, horarios.getUsuario().getIdUsuario());
						dhr.delete(disp);
					}
				}
			}
		}
	}
	
	private void excluirTodasDisponibilidades(long idUsuario) {
		List <DisponibilidadeHorario> horarios = dhr.findAllDisponivelDoMedico(idUsuario);
		for (int i=0; i < horarios.size(); i++) {
			DisponibilidadeHorario disp = horarios.get(i);
			dhr.delete(disp);
		}
	}
}

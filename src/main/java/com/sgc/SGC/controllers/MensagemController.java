package com.sgc.SGC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgc.SGC.models.Mensagem;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;

@Controller
public class MensagemController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	MensagemRepository mer;
	
	@RequestMapping("/mensagem")
	public ModelAndView listaMensagens(Model model) {
		ModelAndView mv = new ModelAndView("mensagem/formListaMensagens");   
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		List<Mensagem> mensagens = mer.findAllMensagensUsuario(usuarioLogado.getIdUsuario());
		for (int i=0; i < mensagens.size();i++) {
			Mensagem mensagem = mensagens.get(i);
			mensagem.setLida('S');
			mer.save(mensagem);
		}
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("mensagens", mensagens);
		return mv;	
	}
	
	@RequestMapping(value="/enviarMensagem", method=RequestMethod.GET)
	public String enviarMensagem(Model model) {
		List<Usuario> usuarios 			= ur.findAllAtivos();
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarios);
		return "mensagem/formEnviarMensagem";
	}
	
	@RequestMapping(value="/enviarMensagem", method=RequestMethod.POST)
	public String gravarMensagem(Mensagem mensagem, Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioRemet = ur.findByLogin(nomeUsuario);
		Usuario usuarioDest  = ur.findByIdUsuario(mensagem.getUsuarioDest().getIdUsuario());
		
		mensagem.setUsuarioRemet(usuarioRemet);
		mensagem.setUsuarioDest(usuarioDest);
		mensagem.setLida('N');
		mer.save(mensagem);		
		return "redirect:/mensagem";
	}
	
	@RequestMapping(value="/excluirMensagem/{idMensagem}")
    public String excluirMensagem(@PathVariable("idMensagem") long idMensagem, RedirectAttributes redirectAttributes) {
    	Mensagem mensagem = mer.findByIdMensagem(idMensagem);
        mer.delete(mensagem);
        redirectAttributes.addFlashAttribute("message", "Mensagem excluida com sucesso.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/mensagem";
    }
	
}

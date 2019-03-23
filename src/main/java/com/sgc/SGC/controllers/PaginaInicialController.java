package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;

@Controller
public class PaginaInicialController {
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping("/paginaInicial")
	public String paginaInicial(Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		
		return "/paginaInicial";	
	}
	
}

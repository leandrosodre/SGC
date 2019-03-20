/*package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;

@Controller
public class FragmentsController {
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping(value="/quantidadeMensagens", method=RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE})
	public String atualizarMensagens(Model model) {
		String quantidade;
		
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		
		quantidade = "{quantidade: " + String.valueOf(quantidadeNaolidas) + "}";
		
		
		return quantidade;
	}
	
}
*/
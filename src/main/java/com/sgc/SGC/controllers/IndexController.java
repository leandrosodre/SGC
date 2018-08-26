package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class IndexController {

	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String login(Model model, @RequestParam("login") String login, @RequestParam("senha") String senha) {
		Usuario usuario = ur.findByLoginESenha(login, senha);
		
		if (usuario == null) {
			model.addAttribute("erroLogin", true);
			return "login";
		}else {
			if (usuario.getStatus() == 'A')
				return "redirect:/paginaInicial";
			else
				return "login";
		}
	}
}

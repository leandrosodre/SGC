package com.sgc.SGC.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Role;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String cadastrarUsuario(Usuario usuario){
		String senha;
		senha = usuario.getSenha();
		BCryptPasswordEncoder sehaCrypt = new BCryptPasswordEncoder();
		String hashedPassword = sehaCrypt.encode(senha);
		usuario.setSenha(hashedPassword);
		Date today = new Date();
		usuario.setDataInicio(today);
		usuario.setDisponivel('I');
		
		if (usuario.getNivel() == 1)
			usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		else
			if (usuario.getNivel() == 2)
				usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
			else
				usuario.setRoles(Arrays.asList(new Role("ROLE_MED")));
		
		ur.save(usuario);
		return "redirect:/usuarios";
	}
	
	@RequestMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("usuario/formListaUsuarios");
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value="/usuarios/{idUsuario}", method=RequestMethod.GET)
	public ModelAndView editarUsuario(@PathVariable("idUsuario") long idUsuario) {
		Usuario usuario = ur.findByIdUsuario(idUsuario);		
		ModelAndView mv = new ModelAndView("usuario/formEditarUsuario");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value="/usuarios/{idUsuario}", method=RequestMethod.POST)
	public String atualizarUsuario(Usuario usuario) {
		if (usuario.getNivel() == 1)
			usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		else
			if (usuario.getNivel() == 2)
				usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
			else
				usuario.setRoles(Arrays.asList(new Role("ROLE_MED")));
		ur.save(usuario);
		return "redirect:/usuarios";
	}
	
    @RequestMapping(value="/usuarios/delete/{idUsuario}")
    public String excluirUsuario(@RequestParam("idUsuario") long idUsuario) {
    	Usuario usuario = ur.findByIdUsuario(idUsuario);
        ur.delete(usuario);
        return "redirect:/usuarios";
    }
}

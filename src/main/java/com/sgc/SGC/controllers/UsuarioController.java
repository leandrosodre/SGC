package com.sgc.SGC.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Pessoa;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.PessoaRepository;
import com.sgc.SGC.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PessoaRepository pr;
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String cadastrarUsuario(Usuario usuario, @RequestParam("cpf") String cpf){
		Pessoa pessoa = pr.findByCPF(cpf);		
		usuario.setPessoa(pessoa);
		Date today = new Date();
		usuario.setDataInicio(today);
		usuario.setDisponivel('I');
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
	public String atualizarUsuario(Usuario usuario, @RequestParam("cpf") String cpf) {
		System.out.println("Cpf :" + cpf);
		Pessoa pessoa = pr.findByCPF(cpf);		
		usuario.setPessoa(pessoa);
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

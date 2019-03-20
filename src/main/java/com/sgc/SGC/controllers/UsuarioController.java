package com.sgc.SGC.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgc.SGC.models.Role;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.AgendaRepository;
import com.sgc.SGC.repository.ExameRepository;
import com.sgc.SGC.repository.HorariosRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.PacienteRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.validacoes.ValidarUsuario;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PacienteRepository pr;
	
	@Autowired
	HorariosRepository hr;
	
	@Autowired
	AgendaRepository ar;
	
	@Autowired
	ExameRepository er;
	
	
	@Autowired
	MensagemRepository mer;
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String form(Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String cadastrarUsuario(Usuario usuario, Model model){
		String senha;
		ValidarUsuario validar = new ValidarUsuario(usuario);
		boolean usuarioValido = validar.usuarioValido();
		
		senha = usuario.getSenha();
		BCryptPasswordEncoder sehaCrypt = new BCryptPasswordEncoder();
		String hashedPassword = sehaCrypt.encode(senha);
		usuario.setSenha(hashedPassword);
		Date today = new Date();
		usuario.setDataInicio(today);
		
		if (usuario.getNivel() == 1)
			usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		else
			if (usuario.getNivel() == 2)
				usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
			else {
				if (usuario.getNivel() == 3)
					usuario.setRoles(Arrays.asList(new Role("ROLE_MED")));
				else
					usuario.setRoles(Arrays.asList(new Role("ROLE_LAB")));
			}
		
		if ( !usuarioValido ) {
			model.addAttribute("erro", true);
			model.addAttribute("mensagem", validar.getMensagem());
			return "usuario/formUsuario";
		}else {
			ur.save(usuario);
			return "redirect:/usuarios";
		}
	}
	
	@RequestMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("usuario/formListaUsuarios");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuarios", usuarios);
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		return mv;
	}
	
	@RequestMapping(value="/usuarios/{idUsuario}", method=RequestMethod.GET)
	public ModelAndView editarUsuario(@PathVariable("idUsuario") long idUsuario) {
		Usuario usuario = ur.findByIdUsuario(idUsuario);
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		
		ModelAndView mv = new ModelAndView("usuario/formEditarUsuario");
		mv.addObject("usuario", usuario);
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		
		return mv;
	}
	
	@RequestMapping(value="/usuarios/{idUsuario}", method=RequestMethod.POST)
	public String atualizarUsuario(Usuario usuario) {
		String senha;
		if (usuario.getSenha().length() < 15) {
			senha = usuario.getSenha();
			BCryptPasswordEncoder sehaCrypt = new BCryptPasswordEncoder();
			String hashedPassword = sehaCrypt.encode(senha);
			usuario.setSenha(hashedPassword);
		}
		if (usuario.getNivel() == 1)
			usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		else
			if (usuario.getNivel() == 2)
				usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
			else {
				if (usuario.getNivel() == 3)
					usuario.setRoles(Arrays.asList(new Role("ROLE_MED")));
				else
					usuario.setRoles(Arrays.asList(new Role("ROLE_LAB")));
			}
		ur.save(usuario);
		return "redirect:/usuarios";
	}
	
    @RequestMapping(value="/usuarios/delete/{idUsuario}")
    public String excluirUsuario(@RequestParam("idUsuario") long idUsuario, RedirectAttributes redirectAttributes) {
    	Usuario usuario = ur.findByIdUsuario(idUsuario);
		usuario.setStatus('I');
		ur.save(usuario);
        redirectAttributes.addFlashAttribute("message", "Usuário inativado com sucesso.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/usuarios";
    }
    
    @RequestMapping("/buscarUsuarios")
   	public ModelAndView buscarUsuarios(@RequestParam("nome") String nome,
   									   @RequestParam("nivel") int nivel,
   									   @RequestParam("status") String status) {
    	
       	ModelAndView mv = new ModelAndView("usuario/formListaUsuarios");   	
       	Iterable<Usuario> usuarios;
       	
       	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		
       	if (nome != "") {
       		usuarios = ur.findAllByName(nome);
       	} else if (nivel != 0) {
       		usuarios = ur.findAllByNivel(nivel);
       	} else if (status != ""){
       		char statusChar = status.charAt(1);
       		usuarios = ur.findAllByStatus(statusChar);
       	} else {
       		usuarios = ur.findAll();
       	}
       	mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
       	mv.addObject("usuarios", usuarios);
   		return mv;
   	}
}

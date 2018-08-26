package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Pessoa;
import com.sgc.SGC.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pr;
	
	@RequestMapping(value="/cadastrarPessoa", method=RequestMethod.GET)
	public String form() {
		return "pessoa/formPessoa";
	}
	
	@RequestMapping(value="/cadastrarPessoa", method=RequestMethod.POST)
	public String form(Pessoa pessoa) {
		pr.save(pessoa);
		return "redirect:/pessoas";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("pessoa/formListaPessoas");
		Iterable<Pessoa> pessoas = pr.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}
	
	@RequestMapping("/pessoas/{idPessoa}")
	public ModelAndView editarPessoa(@PathVariable("idPessoa") long idPessoa) {
		Pessoa pessoa = pr.findByIdPessoa(idPessoa);		
		ModelAndView mv = new ModelAndView("pessoa/formEditarPessoa");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/pessoas/{idPessoa}", method=RequestMethod.POST)
	public String atualizarPessoa(Pessoa pessoa) {
		pr.save(pessoa);
		return "redirect:/pessoas";
	}
	
    @RequestMapping(value="/pessoas/delete/{idPessoa}")
    public String excluirPessoa(@RequestParam("idPessoa") long idPessoa) {
    	Pessoa pessoa = pr.findByIdPessoa(idPessoa);
        pr.delete(pessoa);
        return "redirect:/pessoas";
    }
	
}

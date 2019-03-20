package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgc.SGC.models.Medicamento;
import com.sgc.SGC.models.Usuario;
import com.sgc.SGC.repository.MedicamentoRepository;
import com.sgc.SGC.repository.MensagemRepository;
import com.sgc.SGC.repository.UsuarioRepository;
import com.sgc.SGC.security.BuscarUsuarioAutenticado;
import com.sgc.SGC.validacoes.ValidarMedicamento;

@Controller
public class MedicamentoController {
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	MensagemRepository mer;
	
	@Autowired
	private MedicamentoRepository mr;
	
	@RequestMapping(value="/cadastrarMedicamento", method=RequestMethod.GET)
	public String form(Model model) {
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		model.addAttribute("quantidadeNaolidas", quantidadeNaolidas);
		return "medicamentos/formMedicamento";
	}
	
	@RequestMapping(value="/cadastrarMedicamento", method=RequestMethod.POST)
	public String form(Medicamento medicamento, Model model) {
		ValidarMedicamento validar = new ValidarMedicamento(medicamento);
		boolean medicamentoValido = validar.medicamentoValido();
		
		if ( !medicamentoValido ) {
			model.addAttribute("erro", true);
			model.addAttribute("mensagem", validar.getMensagem());
			return "medicamentos/formMedicamento";
		}else {
			medicamento.setAtivo('S');
			mr.save(medicamento);
			return "redirect:/medicamentos";
		}
	}	
	
	@RequestMapping("/medicamentos")
	public ModelAndView listaMedicamentos() {
		ModelAndView mv = new ModelAndView("medicamentos/formListaMedicamentos");
		
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		
		Iterable<Medicamento> medicamentos = mr.findAllOrderAtivo();
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("medicamentos", medicamentos);
		return mv;
	}
	
	@RequestMapping("/medicamentos/{idMedicamento}")
	public ModelAndView editarMedicamento(@PathVariable("idMedicamento") long idMedicamento) {
		Medicamento medicamento = mr.findByIdMedicamento(idMedicamento);		
		ModelAndView mv = new ModelAndView("medicamentos/formEditarMedicamento");
		String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
		Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
		int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
		mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
		mv.addObject("medicamento", medicamento);
		return mv;
	}
	
	@RequestMapping(value="/medicamentos/{idMedicamento}", method=RequestMethod.POST)
	public String atualizarMedicamento(Medicamento medicamento) {
		medicamento.setAtivo('S');
		mr.save(medicamento);
		return "redirect:/medicamentos";
	}
	
    @RequestMapping(value="/medicamentos/delete/{idMedicamento}")
    public String excluirMedicamento(@RequestParam("idMedicamento") long idMedicamento, RedirectAttributes redirectAttributes ) {
    	Medicamento medicamento = mr.findByIdMedicamento(idMedicamento);
        redirectAttributes.addFlashAttribute("message", "Registro Inativado com sucesso.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    medicamento.setAtivo('N');
        mr.save(medicamento);
	    return "redirect:/medicamentos";
    	
    }
    
    @RequestMapping("/buscarMedicamentos")
	public ModelAndView buscarMedicamentos(@RequestParam("fabrica") String fabrica,
										@RequestParam("generico") String generico,
										@RequestParam("fabricante") String fabricante) {
    	
    	ModelAndView mv = new ModelAndView("medicamentos/formListaMedicamentos");   
    	String nomeUsuario 	 = new BuscarUsuarioAutenticado().getNomeUsuarioLogado();
    	Usuario usuarioLogado = ur.findByLogin(nomeUsuario);
    	int quantidadeNaolidas = mer.findAllMensagensNaoLidas(usuarioLogado.getIdUsuario());
    	mv.addObject("quantidadeNaolidas", quantidadeNaolidas);
    	Iterable<Medicamento> medicamentos;
    	if (fabrica != "") {
    		medicamentos = mr.findByNomeFabricaLike(fabrica);
    	} else if (generico != "") {
    		medicamentos = mr.findByNomeGenericoLike(generico);
    	} else if (fabricante != ""){
    		medicamentos = mr.findByNomeFabricanteLike(fabricante);
    	} else {
    		medicamentos = mr.findAllOrderAtivo();
    	}
    	mv.addObject("medicamentos", medicamentos);
		return mv;
	}    
    
}

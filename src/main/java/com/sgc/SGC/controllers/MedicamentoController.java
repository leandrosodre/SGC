package com.sgc.SGC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sgc.SGC.models.Medicamento;
import com.sgc.SGC.repository.MedicamentoRepository;

@Controller
public class MedicamentoController {
	
	@Autowired
	private MedicamentoRepository mr;

	
	@RequestMapping(value="/cadastrarMedicamento", method=RequestMethod.GET)
	public String form() {
		return "medicamentos/formMedicamento";
	}
	
	@RequestMapping(value="/cadastrarMedicamento", method=RequestMethod.POST)
	public String form(Medicamento medicamento) {
		mr.save(medicamento);
		return "redirect:/medicamentos";
	}	
	
	@RequestMapping("/medicamentos")
	public ModelAndView listaMedicamentos() {
		ModelAndView mv = new ModelAndView("medicamentos/formListaMedicamentos");
		Iterable<Medicamento> medicamentos = mr.findAll();
		mv.addObject("medicamentos", medicamentos);
		return mv;
	}
	
	@RequestMapping("/medicamentos/{idMedicamento}")
	public ModelAndView editarMedicamento(@PathVariable("idMedicamento") long idMedicamento) {
		Medicamento medicamento = mr.findByIdMedicamento(idMedicamento);		
		ModelAndView mv = new ModelAndView("medicamentos/formEditarMedicamento");
		mv.addObject("medicamento", medicamento);
		return mv;
	}
	
	@RequestMapping(value="/medicamentos/{idMedicamento}", method=RequestMethod.POST)
	public String atualizarMedicamento(Medicamento medicamento) {
		mr.save(medicamento);
		return "redirect:/medicamentos";
	}
	
    @RequestMapping(value="/medicamentos/delete/{idMedicamento}")
    public String excluirMedicamento(@RequestParam("idMedicamento") long idMedicamento, Model model) {
    	Medicamento medicamento = mr.findByIdMedicamento(idMedicamento);
	    mr.delete(medicamento);
    	return "redirect:/medicamentos";
    	
    }
    
    @RequestMapping("/buscarMedicamentos")
	public ModelAndView buscarMedicamentos(@RequestParam("fabrica") String fabrica,
										@RequestParam("generico") String generico,
										@RequestParam("fabricante") String fabricante) {
    	
    	ModelAndView mv = new ModelAndView("medicamentos/formListaMedicamentos");   	
    	Iterable<Medicamento> medicamentos;
    	if (fabrica != "") {
    		medicamentos = mr.findByNomeFabricaLike(fabrica);
    	} else if (generico != "") {
    		medicamentos = mr.findByNomeGenericoLike(generico);
    	} else if (fabricante != ""){
    		medicamentos = mr.findByNomeFabricanteLike(fabricante);
    	} else {
    		medicamentos = mr.findAll();
    	}
    	mv.addObject("medicamentos", medicamentos);
		return mv;
	}
}

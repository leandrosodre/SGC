package com.sgc.SGC.validacoes;

import com.sgc.SGC.models.Medicamento;

public class ValidarMedicamento {
	
	private boolean 		medicamentoValido = true;
	private String  		mensagem;
	private Medicamento 	medicamento;
	
	public ValidarMedicamento(Medicamento medicamento) {		
		this.medicamento = medicamento;
	}
	
	public boolean medicamentoValido() {
		
		if (medicamento.getNomeFabrica().isEmpty() || medicamento.getNomeFabricante().isEmpty() || medicamento.getNomeGenerico().isEmpty()) {
			medicamentoValido 	= false;
			mensagem 			= "Preencha todos os campos";
		}
		
		return medicamentoValido; 
	}

	public boolean isMedicamentoValido() {
		return medicamentoValido;
	}

	public String getMensagem() {
		return mensagem;
	}
}

package com.sgc.SGC.validacoes;

import com.sgc.SGC.models.Exame;

public class ValidarExame {
	
	private boolean 		exameValido = true;
	private String  		mensagem;
	private Exame 	exame;
	
	public ValidarExame(Exame exame) {		
		this.exame = exame;
	}
	
	public boolean exameValido() {
		
		if (exame.getPaciente().idPaciente == 0) {
			exameValido = false;
			mensagem 	= "Escolha o paciente";
		}
		
		if (exame.getDataExame() == null) {
			exameValido = false;
			mensagem 	= "Escolha a data do Exame";
		}
		
		if (exame.getTipoExame() == null) {
			exameValido = false;
			mensagem 	= "Escolha o tipo do Exame";
		}
		
		if (exame.getDescricao().isEmpty()) {
			exameValido = false;
			mensagem 	= "Digite a descrição para solicitar o exame";
		}
		
		return exameValido; 
	}

	public boolean isMedicamentoValido() {
		return exameValido;
	}

	public String getMensagem() {
		return mensagem;
	}
}

package com.sgc.SGC.validacoes;

import com.sgc.SGC.models.Paciente;

public class ValidarPaciente {
	
	private boolean 	pacienteValido = true;
	private String  	mensagem;
	private Paciente 	paciente;
	
	public ValidarPaciente(Paciente paciente) {		
		this.paciente = paciente;
	}
	
	public boolean pacienteValido() {
		if ( paciente.getNomeCompleto().isEmpty() ) {
			pacienteValido 		= false;
			mensagem 			= "Digite um nome";
		}
		
		if (paciente.getCpf().length() < 14) {
			pacienteValido 		= false;
			mensagem 			= "Digite um CPF válido";
		}
		
		if (paciente.getCpf().isEmpty()) {
			pacienteValido 		= false;
			mensagem 			= "Digite o CPF";
		}
		
		if ( paciente.getCelular().isEmpty() && paciente.getTelefone().isEmpty() && paciente.getEmail().isEmpty()) {
			pacienteValido 		= false;
			mensagem 			= "Digite ao menos uma forma de contato";
		}	
		
		return pacienteValido;
	}

	public boolean isPacienteValido() {
		return pacienteValido;
	}

	public String getMensagem() {
		return mensagem;
	}
}

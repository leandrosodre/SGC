package com.sgc.SGC.validacoes;

import java.util.Calendar;

import com.sgc.SGC.models.Horarios;

public class ValidarHorarios {
	
	private boolean 	horariosValido = true;
	private String  	mensagem;
	private Horarios 	horarios;
	
	public ValidarHorarios(Horarios horarios) {		
		this.horarios = horarios;
	}
	
	public boolean horariosValido() {
		
		int horaInicio   = horarios.getHoraInicio();
		int minutoInicio = horarios.getMinutoFim();
		int horaFim 	 = horarios.getHoraFim();
		int minutoFim  	 = horarios.getMinutoFim();
		
		Calendar horaInicial = Calendar.getInstance();
		Calendar horaFinal 	 = Calendar.getInstance();
		
		if (horarios.getUsuario().getIdUsuario() == 0) {
			horariosValido 		= false;
			mensagem 			= "Selecione um Médico";
		}
		
		if ( horarios.getDiaSemana() == 0 ) {
			horariosValido 		= false;
			mensagem 			= "Selecione um dia da semana";
		}
		
		horaInicial.set(Calendar.HOUR, horaInicio);
		horaInicial.set(Calendar.MINUTE, minutoInicio);
		
		horaFinal.set(Calendar.HOUR, horaFim);
		horaFinal.set(Calendar.MINUTE, minutoFim);
		
		if (horaInicial.compareTo(horaFinal) >= 0) {
			horariosValido 		= false;
			mensagem 			= "A hora de entrada deve ser inferior a hora de saída";
		}
		
		return horariosValido; 
	}

	public boolean isHorariosValido() {
		return horariosValido;
	}

	public String getMensagem() {
		return mensagem;
	}
}

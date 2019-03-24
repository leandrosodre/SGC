package com.sgc.SGC.validacoes;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.DisponibilidadeHorario;
import com.sgc.SGC.models.Horarios;
import com.sgc.SGC.repository.HorariosRepository;

public class ValidarAgenda {
	
	@Autowired
	HorariosRepository hr;
	
	
	private boolean 	agendaValida = true;
	private String  	mensagem;
	private Agenda 	 	agenda;
	private List<Horarios>    horarios;
	private List<DisponibilidadeHorario> horariosDisponiveis;
	
	public ValidarAgenda(Agenda agenda, List<Horarios> horarios, List<DisponibilidadeHorario> horariosDisponiveis) {		
		this.agenda = agenda;
		this.horarios = horarios;
		this.horariosDisponiveis = horariosDisponiveis;
	}
	
	public boolean horariosValido() {
		boolean horarioValido;
		
		if (agenda.getPaciente().getIdPaciente() == 0) {
			agendaValida 		= false;
			mensagem 			= "Selecione um paciente";
		}
		
		if (agenda.getDataPrevista() == null) {
			agendaValida 		= false;
			mensagem 			= "Digite a data do agendamento";
		}
		
		
		if (agenda.getUsuarioMedico() == null) {
			agendaValida 		= false;
			mensagem 			= "Selecione um médico";
		} else {		
			horarioValido = validarHorarioMedico(agenda, horarios);
			if ( horarioValido == false ) {
				agendaValida 		= false;
				mensagem 			= "Horário não está dentro do horário disponível do Médico";
			} else {
				Calendar horaConsulta = Calendar.getInstance();
				horaConsulta.setTime(agenda.getDataPrevista());
				int diaSemana = horaConsulta.get(Calendar.DAY_OF_WEEK);
				
				for (int i=0; i<horariosDisponiveis.size();i++) {
					DisponibilidadeHorario disp = horariosDisponiveis.get(i);
					if(diaSemana == disp.getDiaSemana()){
						if (disp.getHora() != horaConsulta.get(Calendar.HOUR) && disp.getMinuto() != horaConsulta.get(Calendar.MINUTE)) {
							agendaValida 		= false;
							mensagem 			= "Horário não está dentro das opções de horários disponíveis do Médico, favor digitar um da lista abaixo.";
						} else if (disp.getHora() == horaConsulta.get(Calendar.HOUR) && disp.getMinuto() == horaConsulta.get(Calendar.MINUTE)){
							agendaValida 		= true;
							mensagem 			= "";
							return agendaValida;
						}
						
					}
				}
				
			}
		}
		
		return agendaValida; 
	}

	public boolean isAgendaValida() {
		return agendaValida;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	private boolean validarHorarioMedico(Agenda agenda, List<Horarios> horarios) {
		boolean horarioValido = false;
		
		Calendar horaConsulta = Calendar.getInstance();
		Calendar horaInicial = Calendar.getInstance();
		Calendar horaFinal 	 = Calendar.getInstance();

		horaConsulta.setTime(agenda.getDataPrevista());
		int diaSemana = horaConsulta.get(Calendar.DAY_OF_WEEK);
		
		
		for (int i=0; i < horarios.size(); i++) {
			Horarios horario = horarios.get(i);
			if (horario.getDiaSemana() == diaSemana) {
				horaInicial.set(horaConsulta.get(Calendar.YEAR), horaConsulta.get(Calendar.MONTH), horaConsulta.get(Calendar.DATE), 
								horario.getHoraInicio()-1, horario.getMinutoInicio()-1, 59);
				
				horaFinal.set(horaConsulta.get(Calendar.YEAR), horaConsulta.get(Calendar.MONTH), horaConsulta.get(Calendar.DATE), 
							horario.getHoraFim(), horario.getMinutoFim(), 59);
				
				System.out.println("hora Consulta : " + horaConsulta.toString());
				System.out.println("hora Inicial : " + horaInicial.toString());
				System.out.println("hora final : " + horaFinal.toString());
				
				int comp1, comp2;
				comp1 = horaConsulta.compareTo(horaInicial);
				comp2 = horaConsulta.compareTo(horaFinal);
				
				System.out.println("Compare 1 : " + comp1);
				System.out.println("Compare 2 : " + comp2);
				
				
				if (horaConsulta.compareTo(horaInicial) >= 0 && horaConsulta.compareTo(horaFinal) <= 0) {
					horarioValido = true;
					return horarioValido;
				} else {
					horarioValido = false;
				}
			}
		}
		return horarioValido;
	}
}

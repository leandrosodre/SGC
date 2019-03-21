package com.sgc.SGC.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DisponibilidadeHorario {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long    idHorarios;
	
	private int    diaSemana; //1-Domingo, 2-Segunda, 3-Terça, 4-Quarta, 5-Quinta, 6-Sexta, 7-Sábado
	private int    hora;
	private int    minuto;
	
	private char disponivel;
	
	@ManyToOne
	private Usuario usuario;

	public long getIdHorarios() {
		return idHorarios;
	}

	public void setIdHorarios(long idHorarios) {
		this.idHorarios = idHorarios;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public char getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(char disponivel) {
		this.disponivel = disponivel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

package com.sgc.SGC.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Paciente {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long    idPaciente;
	
	private Date   proximaConsulta;
	private Date   ultimaConsulta;
	
	@OneToOne
	private Pessoa pessoa;
	
	
	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Date getProximaConsulta() {
		return proximaConsulta;
	}

	public void setProximaConsulta(Date proximaConsulta) {
		this.proximaConsulta = proximaConsulta;
	}

	public Date getUltimaConsulta() {
		return ultimaConsulta;
	}

	public void setUltimaConsulta(Date ultimaConsulta) {
		this.ultimaConsulta = ultimaConsulta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

package com.sgc.SGC.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Agenda {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public long    idAgenda;
		
		private Date   dataPrevista;
		private char   resultado;
		
		@ManyToOne
		private Paciente paciente;
		
		@ManyToOne
		private Usuario usuario;

		
		public long getIdAgenda() {
			return idAgenda;
		}

		public void setIdAgenda(long idAgenda) {
			this.idAgenda = idAgenda;
		}

		public Date getDataPrevista() {
			return dataPrevista;
		}

		public void setDataPrevista(Date dataPrevista) {
			this.dataPrevista = dataPrevista;
		}

		public char getResultado() {
			return resultado;
		}

		public void setResultado(char resultado) {
			this.resultado = resultado;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
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

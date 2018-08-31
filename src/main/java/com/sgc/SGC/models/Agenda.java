package com.sgc.SGC.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Agenda {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public long    idAgenda;
		
		private Date   dataPrevista;
		
		@Column(nullable = true)
		private String   resultado;
		
		@ManyToOne
		private Paciente paciente;
		
		@ManyToOne
		public Usuario usuarioMarcador;
		
		@ManyToOne
		public Usuario usuarioMedico;

		
		public Usuario getUsuarioMarcador() {
			return usuarioMarcador;
		}

		public void setUsuarioMarcador(Usuario usuarioMarcador) {
			this.usuarioMarcador = usuarioMarcador;
		}

		public Usuario getUsuarioRealizador() {
			return usuarioMedico;
		}

		public void setUsuarioRealizador(Usuario usuarioMedico) {
			this.usuarioMedico = usuarioMedico;
		}

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

		public String getResultado() {
			return resultado;
		}

		public void setResultado(String resultado) {
			this.resultado = resultado;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
}

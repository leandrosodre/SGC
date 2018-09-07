package com.sgc.SGC.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Agenda {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public long    idAgenda;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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

		public Usuario getUsuarioMedico() {
			return usuarioMedico;
		}

		public void setUsuarioMedico(Usuario usuarioMedico) {
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

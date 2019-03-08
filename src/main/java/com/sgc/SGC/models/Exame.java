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
public class Exame {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public long    idExame;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
		private Date   dataExame;
		
		@Column(nullable = true)
		private String resultado;
		
		private String descricao;
		
		@ManyToOne
		private Paciente paciente;
		
		@ManyToOne
		public Usuario usuarioMedico;
		
		@ManyToOne
		private TipoExame tipoExame;
		
		
		public long getIdExame() {
			return idExame;
		}

		public void setIdExame(long idExame) {
			this.idExame = idExame;
		}

		public Date getDataExame() {
			return dataExame;
		}

		public void setDataExame(Date dataExame) {
			this.dataExame = dataExame;
		}

		public String getResultado() {
			return resultado;
		}

		public void setResultado(String resultado) {
			this.resultado = resultado;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Usuario getUsuarioMedico() {
			return usuarioMedico;
		}

		public void setUsuarioMedico(Usuario usuarioMedico) {
			this.usuarioMedico = usuarioMedico;
		}

		public TipoExame getTipoExame() {
			return tipoExame;
		}

		public void setTipoExame(TipoExame tipoExame) {
			this.tipoExame = tipoExame;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
}

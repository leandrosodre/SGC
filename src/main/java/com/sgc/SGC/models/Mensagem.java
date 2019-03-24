package com.sgc.SGC.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Mensagem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long    idMensagem;
	
	private String textoMensagem;
	private char   lida;
	
	@ManyToOne
	public Usuario usuarioDest;

	@ManyToOne
	public Usuario usuarioRemet;
	
	public Usuario getUsuarioDest() {
		return usuarioDest;
	}
	public void setUsuarioDest(Usuario usuarioDest) {
		this.usuarioDest = usuarioDest;
	}
	public Usuario getUsuarioRemet() {
		return usuarioRemet;
	}
	public void setUsuarioRemet(Usuario usuarioRemet) {
		this.usuarioRemet = usuarioRemet;
	}
	public long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public String getTextoMensagem() {
		return textoMensagem;
	}
	public void setTextoMensagem(String textoMensagem) {
		this.textoMensagem = textoMensagem;
	}
	public char getLida() {
		return lida;
	}
	public void setLida(char lida) {
		this.lida = lida;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

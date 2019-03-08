package com.sgc.SGC.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long    idMedicamento;
	
	private String nomeFabrica;
	private String nomeGenerico;
	private String nomeFabricante;
	
	
	public long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getNomeFabrica() {
		return nomeFabrica;
	}
	public void setNomeFabrica(String nomeFabrica) {
		this.nomeFabrica = nomeFabrica;
	}
	public String getNomeGenerico() {
		return nomeGenerico;
	}
	public void setNomeGenerico(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}
	public String getNomeFabricante() {
		return nomeFabricante;
	}
	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}

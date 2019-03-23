package com.sgc.SGC.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TipoExame implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long    idTipoExame;
	
	
	private String tipoExame;


	public long getIdTipoExame() {
		return idTipoExame;
	}


	public void setIdTipoExame(long idTipoExame) {
		this.idTipoExame = idTipoExame;
	}


	public String getTipoExame() {
		return tipoExame;
	}


	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

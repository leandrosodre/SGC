package com.sgc.SGC.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Role implements GrantedAuthority{
	
	@Id
	@Size(max = 20)
	private String nomeRole;
	
	@ManyToMany
	private List<Usuario> usuarios;
	
	public Role() {
	}
	
	public Role(String nome) {
		this.nomeRole = nome;
	}


	@Override
	public String getAuthority() {
		return this.nomeRole;
	}


	public String getNomeRole() {
		return nomeRole;
	}


	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
}

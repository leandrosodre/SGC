package com.sgc.SGC.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(uniqueConstraints = 
@UniqueConstraint(columnNames = "login", name = "login_unique"))
public class Usuario implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long  idUsuario;
	
	@Size(max = 20)
	private String login;
	private String nomeUsuario;
	private String email;
	private String senha;
	private Date dataInicio;
	private int  nivel; // 1- Administrador / 2- Recepcionista / 3- Medico
	private char status; // A- ativo / I- Inativo
	private char disponivel; //D - Disponivel / I- Indisponivel
	
	@ManyToMany
	@JoinTable(name= "usuario_roles",joinColumns = @JoinColumn(
			name = "usuario_id", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Horarios> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horarios> horarios) {
		this.horarios = horarios;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany
	private List<Horarios> horarios;
	
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(char disponivel) {
		this.disponivel = disponivel;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		if (this.status == 'A')
			return true;
		else
			return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}

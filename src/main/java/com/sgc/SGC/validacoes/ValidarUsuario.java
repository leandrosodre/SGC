package com.sgc.SGC.validacoes;

import com.sgc.SGC.models.Usuario;

public class ValidarUsuario {
	
	private boolean 	usuarioValido = true;
	private String  	mensagem;
	private Usuario 	usuario;
	
	public ValidarUsuario(Usuario usuario) {		
		this.usuario = usuario;
	}
	
	public boolean usuarioValido() {
		if ( usuario.getNomeUsuario().isEmpty() ) {
			usuarioValido 		= false;
			mensagem 			= "Digite um nome";
		}
		
		if (usuario.getLogin().isEmpty()) {
			usuarioValido 		= false;
			mensagem 			= "Digite um login";
		}
		
		if (usuario.getSenha().isEmpty()) {
			usuarioValido 		= false;
			mensagem 			= "Digite uma senha";
		}
		
		return usuarioValido;
	}

	public boolean isUsuarioValido() {
		return usuarioValido;
	}

	public String getMensagem() {
		return mensagem;
	}
}

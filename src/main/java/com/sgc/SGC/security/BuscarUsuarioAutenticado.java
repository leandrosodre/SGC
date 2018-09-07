package com.sgc.SGC.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BuscarUsuarioAutenticado {

	public String getNomeUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String usuarioId= authentication.getName();
		    return usuarioId;
		}
		return null;
	}
	
}

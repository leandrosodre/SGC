package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	Usuario findByIdUsuario(long idUsuario);
	
	@Query("select u from Usuario u where u.login = ?1 and u.senha = ?2")
	Usuario findByLoginESenha(String login, String senha);
	
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findByLogin(String login);
	
	@Query("select u from Usuario u where u.nivel = 3 and u.status = 'A'")
	List<Usuario> findAllMedicos();
	
	@Query("select u from Usuario u where u.status = 'A'")
	List<Usuario> findAllAtivos();

	@Query("select u from Usuario u where u.nomeUsuario like %?1%")
	Iterable<Usuario> findAllByName(String nome);
	
	@Query("select u from Usuario u where u.nivel = ?1")
	Iterable<Usuario> findAllByNivel(int nivel);
	
	@Query("select u from Usuario u where u.status = ?1")
	Iterable<Usuario> findAllByStatus(char status);
}

package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Mensagem;

public interface MensagemRepository extends CrudRepository<Mensagem, String>{
	Mensagem findByIdMensagem(long idMensagem);
	
	@Query("select m from Mensagem m where m.usuarioDest.idUsuario = ?1")
	List<Mensagem> findAllMensagensUsuario(long idUsuario);

	@Query("select count(*) from Mensagem m where m.lida = 'N' and m.usuarioDest.idUsuario = ?1")
	int findAllMensagensNaoLidas(long idUsuario);
}

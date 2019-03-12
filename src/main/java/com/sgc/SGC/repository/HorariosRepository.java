package com.sgc.SGC.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Horarios;

public interface HorariosRepository extends CrudRepository<Horarios, String>{
	
	Horarios findByIdHorarios(long idHorarios);
	
	@Query("select h from Horarios h where h.usuario.nomeUsuario like %?1%")
	Iterable<Horarios> findByNomeUsuario(String nomeUsuario);
	
	@Query("select h from Horarios h where h.diaSemana = ?1")
	Iterable<Horarios> findByDiaSemana(int diaSemana);

}

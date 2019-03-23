package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Horarios;

public interface HorariosRepository extends CrudRepository<Horarios, String>{
	
	Horarios findByIdHorarios(long idHorarios);
	
	@Query("select h from Horarios h where h.usuario.nomeUsuario like %?1%")
	List<Horarios> findByNomeUsuario(String nomeUsuario);
	
	@Query("select h from Horarios h where h.diaSemana = ?1")
	List<Horarios> findByDiaSemana(int diaSemana);

	@Query("select h from Horarios h")
	List<Horarios> findAll();
}

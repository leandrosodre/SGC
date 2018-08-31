package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, String>{
	Agenda findByIdAgenda(long idAgenda);
	
	@Query("select a from Agenda a where a.resultado is null")
	List<Agenda> findAllAgendasEmAberto();
	
	
	
}

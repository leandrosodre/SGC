package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, String>{
	Agenda findByIdAgenda(long idAgenda);
	
	@Query("select a from Agenda a where a.resultado is null")
	List<Agenda> findAllAgendasEmAberto();
	
	@Query("select a from Agenda a where a.paciente.idPaciente = ?1")
	List<Agenda> findAllAgendasDoPaciente(long idPaciente);
	
	@Query("select a from Agenda a where a.usuarioMedico.idUsuario = ?1")
	List<Agenda> findAllAgendasDoMedico(long idUsuario);
	
	
}

package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, String>{
	Agenda findByIdAgenda(long idAgenda);
	
	@Query("select a from Agenda a where a.resultado is null order by dataPrevista")
	List<Agenda> findAllAgendasEmAberto();
	
	@Query("select a from Agenda a where a.resultado is not null order by dataPrevista")
	List<Agenda> findAllAgendasRealizadas();
	
	@Query("select a from Agenda a where a.paciente.idPaciente = ?1 and a.resultado is null order by dataPrevista")
	List<Agenda> findAllAgendasEmAbertoDoPaciente(long idPaciente);
	
	@Query("select a from Agenda a where a.usuarioMedico.idUsuario = ?1 and a.resultado is null  order by dataPrevista")
	List<Agenda> findAllAgendasEmAbertoDoMedico(long idUsuario);
	
	@Query("select a from Agenda a where a.paciente.idPaciente = ?1 and a.resultado is not null order by dataPrevista")
	List<Agenda> findAllAgendasRealizadasDoPaciente(long idPaciente);
	
	@Query("select a from Agenda a where a.usuarioMedico.idUsuario = ?1 and a.resultado is not null order by dataPrevista")
	List<Agenda> findAllAgendasRealizadasDoMedico(long idUsuario);
	
	@Query("select a from Agenda a where a.paciente.idPaciente = ?1 order by dataPrevista")
	List<Agenda> findAllAgendasDoPaciente(long idPaciente);
	
	@Query("select a from Agenda a where a.usuarioMedico.idUsuario = ?1 order by dataPrevista")
	List<Agenda> findAllAgendasDoMedico(long idUsuario);
	
}

package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Exame;

public interface ExameRepository extends CrudRepository<Exame, String>{
	
	
	Exame findByIdExame(long idExame);
	
	@Query("select a from Exame a where a.paciente.idPaciente = ?1")
	List<Exame> findAllExamesDoPaciente(long idPaciente);

	@Query("select a from Exame a where a.resultado is null")
	List<Exame> findAllExamesPendentes();
	
	@Query("select a.paciente.idPaciente from Exame a where a.paciente.idPaciente is not null group by a.paciente.idPaciente")
	List<Long> findAllPacientesComExame();
	
	@Query("select a.paciente.idPaciente from Exame a where a.paciente.idPaciente is not null and a.paciente.nomeCompleto like %?1% group by a.paciente.idPaciente")
	List<Long> findLikePacientesComExame(String nome);
	
}

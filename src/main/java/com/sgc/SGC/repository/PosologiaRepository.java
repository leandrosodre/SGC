package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Agenda;
import com.sgc.SGC.models.Posologia;

public interface PosologiaRepository extends CrudRepository<Posologia, String>{
	
	Posologia findByIdPosologia(long idPosologia);
	
	@Query("select p from Posologia p where p.agenda.idAgenda = ?1")
	List<Posologia> findAllPosologiasDaAgenda(long idAgenda);
	
	@Query("select p.agenda from Posologia p where p.idPosologia = ?1")
	Agenda findAgenda(long idPosologia);

	@Query("select p from Posologia p where p.medicamento.idMedicamento = ?1")
	List<Posologia> findAllPosologiasParaMedicamento(long idMedicamento);
	
	
	
	
}

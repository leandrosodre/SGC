package com.sgc.SGC.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, String>{
	Paciente findByIdPaciente(long idPaciente);
	
	@Query("select p from Paciente p where p.cpf = ?1")
	Paciente findByCPF(String CPF);

}

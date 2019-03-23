package com.sgc.SGC.repository;

import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, String>{
	Consulta findByIdConsulta(long idConsulta);
	
	
}

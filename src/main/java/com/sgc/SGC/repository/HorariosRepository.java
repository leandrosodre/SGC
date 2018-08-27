package com.sgc.SGC.repository;

import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Horarios;

public interface HorariosRepository extends CrudRepository<Horarios, String>{
	
	Horarios findByIdHorarios(long idHorarios);

}

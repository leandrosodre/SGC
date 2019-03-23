package com.sgc.SGC.repository;

import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.TipoExame;

public interface TipoExameRepository extends CrudRepository<TipoExame, String>{
	TipoExame findByIdTipoExame(long idTipoExame);
	
	
}

package com.sgc.SGC.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Medicamento;

public interface MedicamentoRepository extends CrudRepository<Medicamento, String>{
	Medicamento findByIdMedicamento(long idMedicamento);
	
	@Query("select m from Medicamento m where m.nomeFabrica like %?1%")
	Iterable<Medicamento> findByNomeFabricaLike(String nomeFabrica);
	
	@Query("select m from Medicamento m where m.nomeGenerico like %?1%")
	Iterable<Medicamento> findByNomeGenericoLike(String nomeGenerico);
	
	@Query("select m from Medicamento m where m.nomeFabricante like %?1%")
	Iterable<Medicamento> findByNomeFabricanteLike(String nomeFabricante);

}

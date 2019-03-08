package com.sgc.SGC.repository;

import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Medicamento;

public interface MedicamentoRepository extends CrudRepository<Medicamento, String>{
	Medicamento findByIdMedicamento(long idMedicamento);

}

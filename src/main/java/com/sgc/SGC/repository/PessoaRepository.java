package com.sgc.SGC.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{
	Pessoa findByIdPessoa(long idPessoa);
	
	@Query("select p from Pessoa p where p.cpf = ?1")
	Pessoa findByCPF(String CPF);

}

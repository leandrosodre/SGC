package com.sgc.SGC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgc.SGC.models.DisponibilidadeHorario;

public interface DisponibilidadeHorarioRepository extends CrudRepository<DisponibilidadeHorario, String>{
	DisponibilidadeHorario findByIdDispo(long idDispo);
	
	@Query("select d from DisponibilidadeHorario d where d.disponivel = 'S' order by d.diaSemana")
	List<DisponibilidadeHorario> findAllDisponivel();
	
	@Query("select d from DisponibilidadeHorario d where d.disponivel = 'S' and d.usuario.idUsuario = ?1")
	List<DisponibilidadeHorario> findAllDisponivelDoMedico(Long idUsuario);
	
	@Query("select d from DisponibilidadeHorario d where d.diaSemana = ?1 and d.hora = ?2 and d.minuto = ?3 and d.usuario.idUsuario= ?4")
	DisponibilidadeHorario findRegistro(int Diasemana, int hora, int minuto, Long idUsuario);
	
}

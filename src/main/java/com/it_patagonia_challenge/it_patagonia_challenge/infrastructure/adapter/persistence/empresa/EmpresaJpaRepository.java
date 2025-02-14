package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface EmpresaJpaRepository extends JpaRepository<EmpresaDAO, Long>
{

	@Query("select distinct e from EmpresaDAO e inner join  TransferenciaDAO  t on t.empresa = e where  t.fechaTransferencia >= :transferDate")
	List<EmpresaDAO> getEmpresasByTransferDate(@Param("transferDate") LocalDate transferDate);

	@Query("select e from EmpresaDAO e where e.fechaAdhesion >= :addedDate")
	List<EmpresaDAO> getEmpresasByAddedDate(@Param("addedDate") LocalDate addedDate);

	List<EmpresaDAO> findByCuit(Long cuit);
}

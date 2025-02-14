package com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;

import java.time.LocalDate;
import java.util.List;


public interface EmpresaRepositoryPort
{
	List<Empresa> getEmpresasByTransferDate(LocalDate transferDate);

	List<Empresa> getEmpresasByAddedDate(LocalDate addedDate);

	Empresa save(Empresa empresa);

}

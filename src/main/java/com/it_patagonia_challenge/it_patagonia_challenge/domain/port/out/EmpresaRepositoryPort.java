package com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;

import java.time.LocalDate;
import java.util.List;


public interface EmpresaRepositoryPort
{
	List<Empresa> getEmpresasByTransferDate(final LocalDate transferDate);

	List<Empresa> getEmpresasByAddedDate(final LocalDate addedDate);

	Empresa save(final Empresa empresa);

	Empresa findEmpresaByCuit(final Long cuit);

	Empresa findEmpresaByRazonSocial(final String razonSocial);
}

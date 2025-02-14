package com.it_patagonia_challenge.it_patagonia_challenge.domain.port.in;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;

import java.time.LocalDate;
import java.util.List;


public interface EmpresaUseCase
{
	List<Empresa> findEmpresasByTransacciones();

	List<Empresa> findEmpresasByAddedDate();

	Empresa createEmpresa(Empresa empresa);
}

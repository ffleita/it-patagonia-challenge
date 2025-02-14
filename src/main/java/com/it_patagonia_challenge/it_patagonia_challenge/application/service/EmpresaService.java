package com.it_patagonia_challenge.it_patagonia_challenge.application.service;

import com.it_patagonia_challenge.it_patagonia_challenge.application.util.DateUtils;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.in.EmpresaUseCase;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out.EmpresaRepositoryPort;

import java.time.LocalDate;
import java.util.List;


public class EmpresaService implements EmpresaUseCase
{

	private EmpresaRepositoryPort empresaRepositoryPort;

	public EmpresaService(EmpresaRepositoryPort empresaRepositoryPort)
	{
		this.empresaRepositoryPort = empresaRepositoryPort;
	}

	@Override
	public List<Empresa> findEmpresasByTransacciones()
	{
		LocalDate minimumTransaccionDate = DateUtils.getDateThirtyDaysBeforeNow();
		return empresaRepositoryPort.getEmpresasByTransferDate(minimumTransaccionDate);
	}

	@Override
	public List<Empresa> findEmpresasByAddedDate()
	{
		LocalDate minimumAddedDate = DateUtils.getDateThirtyDaysBeforeNow();
		return empresaRepositoryPort.getEmpresasByAddedDate(minimumAddedDate);
	}

	@Override
	public Empresa createEmpresa(Empresa empresa)
	{
		empresa.setFechaAdhesion(LocalDate.now());
		return empresaRepositoryPort.save(empresa);
	}
}

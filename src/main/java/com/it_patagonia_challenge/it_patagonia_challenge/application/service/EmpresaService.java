package com.it_patagonia_challenge.it_patagonia_challenge.application.service;

import com.it_patagonia_challenge.it_patagonia_challenge.application.util.DateUtils;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.DuplicatedEmpresaException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.EmpresasNotFoundException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.MissingRequiredAttributeException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.in.EmpresaUseCase;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out.EmpresaRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class EmpresaService implements EmpresaUseCase
{

	private EmpresaRepositoryPort empresaRepositoryPort;
	private static final Logger log = LoggerFactory.getLogger(EmpresaService.class);

	public EmpresaService(EmpresaRepositoryPort empresaRepositoryPort)
	{
		this.empresaRepositoryPort = empresaRepositoryPort;
	}

	@Override
	public List<Empresa> findEmpresasByTransacciones()
	{
		LocalDate minimumTransaccionDate = DateUtils.getDateThirtyDaysBeforeNow();
		var empresas = empresaRepositoryPort.getEmpresasByTransferDate(minimumTransaccionDate);
		if (empresas.isEmpty())
		{
			throw new EmpresasNotFoundException(
					"No se encontraron empresas con transacciones posteriores a la fecha: " + minimumTransaccionDate);
		}
		return empresas;
	}

	@Override
	public List<Empresa> findEmpresasByAddedDate()
	{
		LocalDate minimumAddedDate = DateUtils.getDateThirtyDaysBeforeNow();
		var empresas = empresaRepositoryPort.getEmpresasByAddedDate(minimumAddedDate);
		if (empresas.isEmpty())
		{
			throw new EmpresasNotFoundException(
					"No se encontraron empresas agregadas luego de la fecha: " + minimumAddedDate);
		}
		return empresas;
	}

	@Override
	public Empresa createEmpresa(Empresa empresa)
	{
		validateEmpresa(empresa);
		empresa.setFechaAdhesion(LocalDate.now());
		return empresaRepositoryPort.save(empresa);
	}

	public void validateEmpresa(final Empresa empresa)
	{
		if (empresa.getCuit() == null)
		{
			throw new MissingRequiredAttributeException("El atributo 'cuit' es obligatorio");
		}
		if (empresa.getRazonSocial() == null || empresa.getRazonSocial().isEmpty())
		{
			throw new MissingRequiredAttributeException("El atributo 'razonSocial' es obligatorio");
		}
		if (empresaRepositoryPort.findEmpresaByCuit(empresa.getCuit()) != null)
		{
			throw new DuplicatedEmpresaException("La empresa con cuit: " + empresa.getCuit() + " ya existe.");
		}
		if (empresaRepositoryPort.findEmpresaByRazonSocial(empresa.getRazonSocial()) != null)
		{
			throw new DuplicatedEmpresaException(
					"La empresa con razon social: " + empresa.getRazonSocial() + " ya existe.");
		}
	}
}

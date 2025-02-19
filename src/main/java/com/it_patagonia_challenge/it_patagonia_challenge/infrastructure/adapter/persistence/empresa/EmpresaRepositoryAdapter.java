package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out.EmpresaRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmpresaRepositoryAdapter implements EmpresaRepositoryPort
{

	private EmpresaJpaRepository empresaJpaRepository;

	public EmpresaRepositoryAdapter(EmpresaJpaRepository empresaJpaRepository)
	{
		this.empresaJpaRepository = empresaJpaRepository;
	}

	@Override
	public List<Empresa> getEmpresasByTransferDate(final LocalDate transferDate)
	{
		final List<EmpresaDAO> empresasWithTransferenciasAfterGivenDate = empresaJpaRepository.getEmpresasByTransferDate(transferDate);
		return empresasWithTransferenciasAfterGivenDate.stream()
				.map(empresaDAO -> new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(), empresaDAO.getFechaAdhesion()))
				.toList();
	}

	@Override
	public List<Empresa> getEmpresasByAddedDate(final LocalDate addedDate)
	{
		final List<EmpresaDAO> empresasWithAddedDateAfterGivenDate = empresaJpaRepository.getEmpresasByAddedDate(addedDate);
		return empresasWithAddedDateAfterGivenDate.stream()
				.map(empresaDAO -> new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(), empresaDAO.getFechaAdhesion()))
				.toList();
	}

	@Override
	public Empresa save(final Empresa empresa)
	{
		final EmpresaDAO empresaDAO = new EmpresaDAO(empresa.getCuit(), empresa.getRazonSocial(), empresa.getFechaAdhesion());
		final EmpresaDAO savedEmpresa = empresaJpaRepository.save(empresaDAO);
		return new Empresa(savedEmpresa.getCuit(), savedEmpresa.getRazonSocial(), savedEmpresa.getFechaAdhesion());
	}

	@Override
	public Empresa findEmpresaByCuit(final Long cuit)
	{
		final var empresaDAO = empresaJpaRepository.findByCuit(cuit);
		if (empresaDAO == null) {
			return null;
		}
		return new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(), empresaDAO.getFechaAdhesion());
	}

	@Override
	public Empresa findEmpresaByRazonSocial(final String razonSocial)
	{
		final var empresaDAO = empresaJpaRepository.findByRazonSocial(razonSocial);
		if (empresaDAO == null) {
			return null;
		}
		return new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(), empresaDAO.getFechaAdhesion());
	}
}

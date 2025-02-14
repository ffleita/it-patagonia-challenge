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
	public List<Empresa> getEmpresasByTransferDate(LocalDate transferDate)
	{
		List<EmpresaDAO> empresasWithTransferenciasAfterGivenDate = empresaJpaRepository.getEmpresasByTransferDate(transferDate);
		return empresasWithTransferenciasAfterGivenDate.stream().map(empresaDAO ->
				new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(),  empresaDAO.getFechaAdhesion())).toList();
	}

	@Override
	public List<Empresa> getEmpresasByAddedDate(LocalDate addedDate)
	{
		List<EmpresaDAO> empresasWithAddedDateAfterGivenDate = empresaJpaRepository.getEmpresasByAddedDate(addedDate);
		return empresasWithAddedDateAfterGivenDate.stream().map(empresaDAO ->
				new Empresa(empresaDAO.getCuit(), empresaDAO.getRazonSocial(),  empresaDAO.getFechaAdhesion())).toList();
	}

	@Override
	public Empresa save(Empresa empresa)
	{
		EmpresaDAO empresaDAO = new EmpresaDAO(empresa.getCuit(), empresa.getRazonSocial(), empresa.getFechaAdhesion());
		EmpresaDAO savedEmpresa = empresaJpaRepository.save(empresaDAO);
		return new Empresa(savedEmpresa.getCuit(), savedEmpresa.getRazonSocial(),  savedEmpresa.getFechaAdhesion());
	}
}

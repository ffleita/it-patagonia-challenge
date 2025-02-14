package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.rest;


import com.it_patagonia_challenge.it_patagonia_challenge.application.service.EmpresaService;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/empresas")
public class EmpresaController
{
	private final EmpresaService empresaService;

	public EmpresaController(EmpresaService empresaService)
	{
		this.empresaService = empresaService;
	}

	@GetMapping("/transferencias/date")
	public List<Empresa> getEmpresasWithTransferenciasAfterDate() {
		return empresaService.findEmpresasByTransacciones();
	}

	@GetMapping("/added/date")
	public List<Empresa> getEmpresasWithAddedDateAfterDate()
	{
		return empresaService.findEmpresasByAddedDate();
	}

	@PostMapping()
	public Empresa crearEmpresa(@RequestBody Empresa empresa)
	{
		return empresaService.createEmpresa(empresa);
	}
}

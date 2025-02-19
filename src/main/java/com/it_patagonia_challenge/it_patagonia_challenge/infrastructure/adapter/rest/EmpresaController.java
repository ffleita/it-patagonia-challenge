package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.rest;


import com.it_patagonia_challenge.it_patagonia_challenge.application.service.EmpresaService;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/empresas")
public class EmpresaController
{
	private final EmpresaService empresaService;

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	public EmpresaController(EmpresaService empresaService)
	{
		this.empresaService = empresaService;
	}

	@GetMapping("/transferencias/date")
	public ResponseEntity<List<Empresa>> getEmpresasWithTransferenciasAfterDate() {
		final var empresas = empresaService.findEmpresasByTransacciones();
		return ResponseEntity.ok(empresas);
	}

	@GetMapping("/added/date")
	public ResponseEntity<List<Empresa>> getEmpresasWithAddedDateAfterDate()
	{
		final var empresas = empresaService.findEmpresasByAddedDate();
		return ResponseEntity.ok(empresas);
	}

	@PostMapping()
	public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa)
	{
		final var response =  empresaService.createEmpresa(empresa);
		return ResponseEntity.ok(response);
	}
}

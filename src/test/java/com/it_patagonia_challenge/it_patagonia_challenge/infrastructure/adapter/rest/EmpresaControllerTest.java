package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.rest;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa.EmpresaDAO;
import com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa.EmpresaJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("integration")
class EmpresaControllerTest
{
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EmpresaJpaRepository empresaJpaRepository;

	@Test
	@Transactional
	void testCrearEmpresaConBaseDeDatos() throws Exception
	{
		Empresa empresa = new Empresa(111L, "Nueva Empresa", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);

		ResponseEntity<Empresa> response = restTemplate.exchange("/empresas", HttpMethod.POST, request, Empresa.class);

		assertEquals(OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Nueva Empresa", response.getBody().getRazonSocial());

		EmpresaDAO emp = empresaJpaRepository.findByCuit(111L);
		assertFalse(emp == null);
		assertEquals("Nueva Empresa", emp.getRazonSocial());
	}

	@Test
	@Transactional
	void testCrearEmpresaConBaseDeDatos_mustReturnComflictStatusDueToCuit() throws Exception
	{
		Empresa empresa = new Empresa(111L, "Nueva Empresa", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);
		restTemplate.exchange("/empresas", HttpMethod.POST, request, Empresa.class);

		Empresa empresa2 = new Empresa(111L, "Empresa con razon social diferente, mismo cuit", null);
		HttpEntity<Empresa> request2 = new HttpEntity<>(empresa2, headers);
		ResponseEntity<String> response = restTemplate.exchange("/empresas", HttpMethod.POST, request2, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}

	@Test
	@Transactional
	void testCrearEmpresaConBaseDeDatos_mustReturnConflictHttpStatusDueToRazonSocial() throws Exception
	{
		Empresa empresa = new Empresa(111L, "Nueva Empresa", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);
		restTemplate.exchange("/empresas", HttpMethod.POST, request, Empresa.class);

		Empresa empresa2 = new Empresa(112L, "Nueva Empresa", null);
		HttpEntity<Empresa> request2 = new HttpEntity<>(empresa2, headers);
		ResponseEntity<String> response = restTemplate.exchange("/empresas", HttpMethod.POST, request2, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}

	@Test
	void testCrearEmpresaConBaseDeDatos_mustReturnBadRequestHttpStatusDueMissingAttributeCuit() throws Exception
	{
		Empresa empresa = new Empresa(null, "Nueva Empresa", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);
		ResponseEntity<String> response = restTemplate.exchange("/empresas", HttpMethod.POST, request, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void testCrearEmpresaConBaseDeDatos_mustReturnBadRequestHttpStatusDueMissingOrEmptyAttributeRazonSocial() throws Exception
	{
		Empresa empresa = new Empresa(111L, "", null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);
		ResponseEntity<String> response = restTemplate.exchange("/empresas", HttpMethod.POST, request, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		Empresa empresa2 = new Empresa(111L, "", null);
		HttpHeaders headers2 = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Empresa> request2 = new HttpEntity<>(empresa2, headers2);
		ResponseEntity<String> response2 = restTemplate.exchange("/empresas", HttpMethod.POST, request2, String.class);

		assertNotNull(response2);
		assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
	}

	@Test
	void testGetEmpresasWithTransferenciasAfterDate()
	{
		ResponseEntity<List> response = restTemplate.exchange("/empresas/transferencias/date", HttpMethod.GET, null, List.class);

		assertEquals(OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);
	}

	@Test
	void testGetEmpresasWithAddedDateAfterDate()
	{
		ResponseEntity<List> response = restTemplate.exchange("/empresas/added/date", HttpMethod.GET, null, List.class);

		assertEquals(OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);
	}
}

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
		void testCrearEmpresaConBaseDeDatos() throws Exception {
			Empresa empresa = new Empresa(111L, "Nueva Empresa", null);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);

			ResponseEntity<Empresa> response = restTemplate.exchange(
					"/empresas",
					HttpMethod.POST,
					request,
					Empresa.class
			);

			assertEquals(OK, response.getStatusCode());
			assertNotNull(response.getBody());
			assertEquals("Nueva Empresa", response.getBody().getRazonSocial());

			List<EmpresaDAO> empresas = empresaJpaRepository.findByCuit(111L);
			assertFalse(empresas.isEmpty());
			assertEquals("Nueva Empresa", empresas.getFirst().getRazonSocial());
		}

		@Test
		void testGetEmpresasWithTransferenciasAfterDate() {
			ResponseEntity<List> response = restTemplate.exchange(
					"/empresas/transferencias/date",
					HttpMethod.GET,
					null,
					List.class
			);

			assertEquals(OK, response.getStatusCode());
			assertTrue(response.getBody().size() > 0);
		}

		@Test
		void testGetEmpresasWithAddedDateAfterDate() {
			ResponseEntity<List> response = restTemplate.exchange(
					"/empresas/added/date",
					HttpMethod.GET,
					null,
					List.class
			);

			assertEquals(OK, response.getStatusCode());
			assertTrue(response.getBody().size() > 0);
		}
}

package com.it_patagonia_challenge.it_patagonia_challenge.application.service;

import com.it_patagonia_challenge.it_patagonia_challenge.application.util.DateUtils;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.DuplicatedEmpresaException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.EmpresasNotFoundException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.MissingRequiredAttributeException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out.EmpresaRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EmpresaServiceTest
{

	@Mock
	private EmpresaRepositoryPort empresaRepositoryPort;

	@InjectMocks
	private EmpresaService empresaService;

	private LocalDate dummyDate;

	private static MockedStatic<DateUtils> mockedStatic;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		dummyDate = LocalDate.of(2025, 1, 15);
		mockedStatic = mockStatic(DateUtils.class);
		when(DateUtils.getDateThirtyDaysBeforeNow()).thenReturn(dummyDate);
	}

	@AfterEach
	void close() {
		mockedStatic.close();
	}

	@Test
	void findEmpresasByTransacciones_ShouldReturnEmpresas1y3()
	{
		Empresa empresa1 = new Empresa(1L, "Empresa 1", LocalDate.of(2025, 2, 16));
		Empresa empresa2 = new Empresa(1L, "Empresa 2", LocalDate.of(2025, 1, 1));
		Empresa empresa3 = new Empresa(1L, "Empresa 3", LocalDate.of(2025, 1, 22));

		when(empresaRepositoryPort.getEmpresasByTransferDate(dummyDate)).thenReturn(Arrays.asList(empresa1, empresa3));

		List<Empresa> empresas = empresaService.findEmpresasByTransacciones();

		assertEquals(2, empresas.size());
		assertEquals(empresa1, empresas.get(0));
		assertEquals(empresa3, empresas.get(1));
		verify(empresaRepositoryPort, times(1)).getEmpresasByTransferDate(dummyDate);
	}

	@Test
	void findEmpresasByTransacciones_ShouldThrowEmpresasNotFoundException()
	{

		when(empresaRepositoryPort.getEmpresasByTransferDate(dummyDate)).thenReturn(List.of());

		assertThrows(EmpresasNotFoundException.class, () -> empresaService.findEmpresasByTransacciones());

		verify(empresaRepositoryPort, times(1)).getEmpresasByTransferDate(dummyDate);
	}

	@Test
	void findEmpresasByAddedDate_ShouldReturnEmpresas1y2()
	{
		Empresa empresa1 = new Empresa(1L, "Empresa 1", LocalDate.of(2025, 2, 16));
		Empresa empresa2 = new Empresa(1L, "Empresa 2", LocalDate.of(2025, 1, 1));
		Empresa empresa3 = new Empresa(1L, "Empresa 3", LocalDate.of(2025, 1, 22));

		when(empresaRepositoryPort.getEmpresasByAddedDate(dummyDate)).thenReturn(Arrays.asList(empresa1, empresa2));

		List<Empresa> empresas = empresaService.findEmpresasByAddedDate();

		assertEquals(2, empresas.size());
		assertEquals(empresa1, empresas.get(0));
		assertEquals(empresa2, empresas.get(1));
		verify(empresaRepositoryPort, times(1)).getEmpresasByAddedDate(dummyDate);
	}

	@Test
	void findEmpresasByAddedDate_ShouldReturnEmptyList()
	{
		when(empresaRepositoryPort.getEmpresasByAddedDate(dummyDate)).thenReturn(List.of());

		assertThrows(EmpresasNotFoundException.class, () -> empresaService.findEmpresasByAddedDate());

		verify(empresaRepositoryPort, times(1)).getEmpresasByAddedDate(dummyDate);
	}

	@Test
	void createEmpresa()
	{
		Empresa nuevaEmpresa = new Empresa(1L, "Empresa 1", null);
		Empresa empresaGuardada = new Empresa(1L, "Empresa 1", LocalDate.now());

		when(empresaRepositoryPort.save(any(Empresa.class))).thenReturn(empresaGuardada);

		Empresa empresa = empresaService.createEmpresa(nuevaEmpresa);

		assertNotNull(empresa);
		assertEquals("Empresa 1", empresa.getRazonSocial());
		assertNotNull(empresa.getFechaAdhesion());
		verify(empresaRepositoryPort, times(1)).save(any(Empresa.class));
	}

	@Test
	void createEmpresa_shouldThrowExceptionDueToMissingRazonSocial()
	{
		Empresa nuevaEmpresa = new Empresa(111L, "", null);
		assertThrows(MissingRequiredAttributeException.class, () -> empresaService.createEmpresa(nuevaEmpresa));
		verify(empresaRepositoryPort, times(0)).save(any(Empresa.class));

		Empresa nuevaEmpresa2 = new Empresa(111L, null, null);
		assertThrows(MissingRequiredAttributeException.class, () -> empresaService.createEmpresa(nuevaEmpresa2));
		verify(empresaRepositoryPort, times(0)).save(any(Empresa.class));
	}

	@Test
	void createEmpresa_shouldThrowExceptionDueToMissingCuit()
	{
		Empresa nuevaEmpresa = new Empresa(null, "Empresa 1", null);

		assertThrows(MissingRequiredAttributeException.class, () -> empresaService.createEmpresa(nuevaEmpresa));
		verify(empresaRepositoryPort, times(0)).save(any(Empresa.class));
	}

	@Test
	void createEmpresa_shouldThrowExceptionDueToDuplicatedEmpresa_CuitCase()
	{
		Empresa empresa = new Empresa(1L, "Empresa 1", null);
		Empresa nuevaEmpresa = new Empresa(1L, "Empresa 1", null);

		when(empresaRepositoryPort.findEmpresaByCuit(any())).thenReturn(nuevaEmpresa);

		assertThrows(DuplicatedEmpresaException.class, () -> empresaService.createEmpresa(empresa));
		verify(empresaRepositoryPort, times(0)).save(any(Empresa.class));
	}

	@Test
	void createEmpresa_shouldThrowExceptionDueToDuplicatedEmpresa_RazonSocialCase()
	{
		Empresa empresa = new Empresa(1L, "Empresa 1", null);
		Empresa nuevaEmpresa = new Empresa(1L, "Empresa 1", null);

		when(empresaRepositoryPort.findEmpresaByRazonSocial(any())).thenReturn(nuevaEmpresa);

		assertThrows(DuplicatedEmpresaException.class, () -> empresaService.createEmpresa(empresa));
		verify(empresaRepositoryPort, times(0)).save(any(Empresa.class));
	}
}

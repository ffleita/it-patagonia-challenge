package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.configuration;

import com.it_patagonia_challenge.it_patagonia_challenge.application.service.EmpresaService;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.port.out.EmpresaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration
{

	@Bean
	public EmpresaService empresaService(EmpresaRepositoryPort empresaRepositoryPort)
	{
		return new EmpresaService(empresaRepositoryPort);
	}
}

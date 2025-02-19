package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity
@Table(name = "empresa")
public class EmpresaDAO
{
	@Id
	@NotNull(message = "El campo 'cuit' no puede ser nulo.")
	private Long cuit;
	@NotNull(message = "El campo 'razonSocial' no puede ser nulo.")
	@NotEmpty(message = "El campo 'razon social' no puede ser vacío.")
	@Column(unique = true)
	private String razonSocial;
	private LocalDate fechaAdhesion;

	public EmpresaDAO()
	{
	}

	public EmpresaDAO(Long cuit, String razonSocial, LocalDate fechaAdhesion)
	{
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.fechaAdhesion = fechaAdhesion;
	}

	public Long getCuit()
	{
		return cuit;
	}

	public void setCuit(Long cuit)
	{
		this.cuit = cuit;
	}

	public String getRazonSocial()
	{
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public LocalDate getFechaAdhesion()
	{
		return fechaAdhesion;
	}

	public void setFechaAdhesion(LocalDate fechaAdhesion)
	{
		this.fechaAdhesion = fechaAdhesion;
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("EmpresaDAO{");
		sb.append("cuit=").append(cuit);
		sb.append(", razonSocial='").append(razonSocial).append('\'');
		sb.append(", fechaAdhesion=").append(fechaAdhesion);
		sb.append('}');
		return sb.toString();
	}
}

package com.it_patagonia_challenge.it_patagonia_challenge.domain.model;

import java.time.LocalDate;


public class Empresa
{
	private Long cuit;
	private String razonSocial;
	private LocalDate fechaAdhesion;

	public Empresa()
	{
	}

	public Empresa(Long cuit, String razonSocial)
	{
		this.cuit = cuit;
		this.razonSocial = razonSocial;
	}

	public Empresa(Long cuit, String razonSocial, LocalDate fechaAdhesion)
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
}


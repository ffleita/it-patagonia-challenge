package com.it_patagonia_challenge.it_patagonia_challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Transferencia
{
	private Long id;
	private BigDecimal importe;
	private String cuentaDebito;
	private String cuentaCredito;
	private Empresa empresa;
	private LocalDate fechaTransferencia;

	public Transferencia()
	{
	}

	public Transferencia(Long id, BigDecimal importe, String cuentaDebito, String cuentaCredito, Empresa empresa, LocalDate fechaTransferencia)
	{
		this.id = id;
		this.importe = importe;
		this.cuentaDebito = cuentaDebito;
		this.cuentaCredito = cuentaCredito;
		this.empresa = empresa;
		this.fechaTransferencia = fechaTransferencia;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigDecimal getImporte()
	{
		return importe;
	}

	public void setImporte(BigDecimal importe)
	{
		this.importe = importe;
	}

	public String getCuentaDebito()
	{
		return cuentaDebito;
	}

	public void setCuentaDebito(String cuentaDebito)
	{
		this.cuentaDebito = cuentaDebito;
	}

	public String getCuentaCredito()
	{
		return cuentaCredito;
	}

	public void setCuentaCredito(String cuentaCredito)
	{
		this.cuentaCredito = cuentaCredito;
	}

	public Empresa getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(Empresa empresa)
	{
		this.empresa = empresa;
	}

	public LocalDate getFechaTransferencia()
	{
		return fechaTransferencia;
	}

	public void setFechaTransferencia(LocalDate fechaTransferencia)
	{
		this.fechaTransferencia = fechaTransferencia;
	}
}

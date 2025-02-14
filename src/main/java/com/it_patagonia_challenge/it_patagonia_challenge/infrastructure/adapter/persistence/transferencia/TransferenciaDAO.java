package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.transferencia;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.model.Empresa;
import com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.adapter.persistence.empresa.EmpresaDAO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "transferencia")
public class TransferenciaDAO
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal importe;
	private String cuentaDebito;
	private String cuentaCredito;
	@ManyToOne(fetch = FetchType.LAZY)
	private EmpresaDAO empresa;
	private LocalDate fechaTransferencia;



	public TransferenciaDAO()
	{
	}

	public TransferenciaDAO(Long id, BigDecimal importe, String cuentaDebito, String cuentaCredito, EmpresaDAO empresa, LocalDate fechaTransferencia)
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

	public EmpresaDAO getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(Empresa EmpresaDAO)
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

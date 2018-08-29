package com.atc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGOAFILIADO")
public class PagoAfiliado extends Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID_PAGOAFILIADO")
	private Long id;
	private Date vencimiento;
	private String captador;
	private String asesor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AFILIACION_ID")
	private Afiliacion afiliacion;

	public PagoAfiliado() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getCaptador() {
		return captador;
	}

	public void setCaptador(String captador) {
		this.captador = captador;
	}

	public String getAsesor() {
		return asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public Afiliacion getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PagoAfiliado))
			return false;
		return id != null && id.equals(((PagoAfiliado) o).id);
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return afiliacion.getAfiliado().getNombre() + " / " + vencimiento;
	}

}

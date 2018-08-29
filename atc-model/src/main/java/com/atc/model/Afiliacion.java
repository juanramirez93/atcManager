package com.atc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AFILIACION")
public class Afiliacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID_AFILIACION")
	private Long id;
	@OneToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona afiliado;
	private Date fechaAfiliacion;
	private long codigoCarnet;
	private String estrato;
	private String escolaridad;
	@OneToMany(mappedBy = "afiliacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PagoAfiliado> pagos = new ArrayList<PagoAfiliado>();
	private Date ultimoPago;
	private int vehiculosParticulares;
	private int vehiculosCarga;
	private String placasParticulares;
	private String placasCarga;
	private String tipoAfiliado;
	
	public Afiliacion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Persona afiliado) {
		this.afiliado = afiliado;
	}

	public Date getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(Date fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public long getCodigoCarnet() {
		return codigoCarnet;
	}

	public void setCodigoCarnet(long codigoCarnet) {
		this.codigoCarnet = codigoCarnet;
	}

	public String getEstrato() {
		return estrato;
	}

	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public List<PagoAfiliado> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoAfiliado> pagos) {
		this.pagos = pagos;
	}

	public void addPago(PagoAfiliado pago) {
		pagos.add(pago);
		pago.setAfiliacion(this);
	}

	public void removePago(PagoAfiliado pago) {
		pagos.remove(pago);
		pago.setAfiliacion(null);
	}

	public Date getUltimoPago() {
		return ultimoPago;
	}

	public void setUltimoPago(Date ultimoPago) {
		this.ultimoPago = ultimoPago;
	}

	public int getVehiculosParticulares() {
		return vehiculosParticulares;
	}

	public void setVehiculosParticulares(int vehiculosParticulares) {
		this.vehiculosParticulares = vehiculosParticulares;
	}

	public int getVehiculosCarga() {
		return vehiculosCarga;
	}

	public void setVehiculosCarga(int vehiculosCarga) {
		this.vehiculosCarga = vehiculosCarga;
	}

	public String getPlacasParticulares() {
		return placasParticulares;
	}

	public void setPlacasParticulares(String placasParticulares) {
		this.placasParticulares = placasParticulares;
	}

	public String getPlacasCarga() {
		return placasCarga;
	}

	public void setPlacasCarga(String placasCarga) {
		this.placasCarga = placasCarga;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
}

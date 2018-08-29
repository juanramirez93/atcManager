package com.atc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISOS")
public class Permisos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_PERMISOS")
	private int id;
	private String nombre;

	private int empresa;
	private int poliza;
	private int producto;
	private int empleado;
	private int usuario;
	private int stock;
	private int movimiento;
	private int reportes;
	private int reintegro;
	private int naturalPermiso;
	private int afiliacion;
	private int pagoAfiliacion;

	public Permisos() {
	}

	public int getNaturalPermiso() {
		return naturalPermiso;
	}

	public void setNaturalPermiso(int naturalPermiso) {
		this.naturalPermiso = naturalPermiso;
	}

	public Permisos(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public int getPoliza() {
		return poliza;
	}

	public void setPoliza(int poliza) {
		this.poliza = poliza;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getEmpleado() {
		return empleado;
	}

	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}

	public int getReportes() {
		return reportes;
	}

	public void setReportes(int reportes) {
		this.reportes = reportes;
	}

	public int getReintegro() {
		return reintegro;
	}

	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}

	public int getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(int afiliacion) {
		this.afiliacion = afiliacion;
	}

	public int getPagoAfiliacion() {
		return pagoAfiliacion;
	}

	public void setPagoAfiliacion(int pagoAfiliacion) {
		this.pagoAfiliacion = pagoAfiliacion;
	}
}

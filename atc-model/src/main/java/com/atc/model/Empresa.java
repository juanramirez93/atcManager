package com.atc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE_EMPRESA")
public class Empresa extends Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_EMPRESA")
	private int id;
	@Column(unique = true)
	private long nit;
	private int digito;
	private String razonSocial;
	private String siglas;
	private String telefono;
	private String celular;
	private String otroTel;
	private String direccion;
	private String ciudad;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTACTO")
	private Contacto contacto;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REPRESENTANTE")
	private Representante representante;

	public Empresa() {
		super();
		tipo = "Empresa";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
		identificacion = nit;
	}

	public int getDigito() {
		return digito;
	}

	public void setDigito(int digito) {
		this.digito = digito;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
		if (siglas.isEmpty()) {
			super.nombre = razonSocial;
		} else {
			super.nombre = siglas;
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getOtroTel() {
		return otroTel;
	}

	public void setOtroTel(String otroTel) {
		this.otroTel = otroTel;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

}

package com.atc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_PRODUCTO")
	private int id;
	protected String modalidad;
	protected int urbanos;
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	protected Empresa empresa;
	protected String tipo;
	protected int urbanoVida;
	protected int urbanoRCE;
	@ManyToMany
	@JoinColumn(name = "ID_COMISION")
	protected List<Comision> comisionRCE;
	@ManyToMany
	@JoinColumn(name = "ID_COMISION")
	protected List<Comision> comisionVida;
	@ManyToMany
	@JoinColumn(name = "ID_COMISION")
	protected List<Comision> comisionTransporte;

	public int getUrbanoVida() {
		return urbanoVida;
	}

	public void setUrbanoVida(int urbanoVida) {
		this.urbanoVida = urbanoVida;
	}

	public int getUrbanoRCE() {
		return urbanoRCE;
	}

	public void setUrbanoRCE(int urbanoRCE) {
		this.urbanoRCE = urbanoRCE;
	}

	public Producto() {
	}

	public Producto(String modalidad, int urbanos, Empresa empresa, String tipo) {
		this.modalidad = modalidad;
		this.urbanos = urbanos;
		this.empresa = empresa;
		this.tipo = tipo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public int getUrbanos() {
		return urbanos;
	}

	public void setUrbanos(int urbanos) {
		this.urbanos = urbanos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String nombre;
		if (empresa.getSiglas().isEmpty()) {
			nombre = empresa.getRazonSocial();
		} else {
			nombre = empresa.getSiglas();
		}
		return nombre + " (" + tipo + ")";
	}

	public List<Comision> getComision() {
		return comisionRCE;
	}

	public void setComision(List<Comision> comision) {
		this.comisionRCE = comision;
	}

	public List<Comision> getComisionRCE() {
		return comisionRCE;
	}

	public void setComisionRCE(List<Comision> comisionRCE) {
		this.comisionRCE = comisionRCE;
	}

	public List<Comision> getComisionVida() {
		return comisionVida;
	}

	public void setComisionVida(List<Comision> comisionVida) {
		this.comisionVida = comisionVida;
	}

	public List<Comision> getComisionTransporte() {
		return comisionTransporte;
	}

	public void setComisionTransporte(List<Comision> comisionTransporte) {
		this.comisionTransporte = comisionTransporte;
	}

}

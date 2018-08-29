package com.atc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "POLIZA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Poliza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_POLIZA")
	private int id;
	protected long numero;
	protected String ramo;
	protected Date inicio;
	protected Date fin;
	protected int valor;

	public Poliza(String ramo) {
		this.ramo = ramo;
	}

	public Poliza() {
	}

	public Poliza(long numero, String ramo, Date inicio, Date fin, int valor, Empresa empresa) {
		this.numero = numero;
		this.ramo = ramo;
		this.inicio = inicio;
		this.fin = fin;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}

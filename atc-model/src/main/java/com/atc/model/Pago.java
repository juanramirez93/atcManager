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
@Table(name = "PAGO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_PAGO")
	protected Long id;
	protected Date fecha;
	protected int reciboDeCaja;
	protected int valor;
	
	public Pago() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getReciboDeCaja() {
		return reciboDeCaja;
	}

	public void setReciboDeCaja(int reciboDeCaja) {
		this.reciboDeCaja = reciboDeCaja;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	
	
}

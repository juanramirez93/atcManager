package com.atc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RCE")
public class RCE extends Poliza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_RCE")
	private int id;
	private int valorUrbanos;
	private double comisionATC;

	public RCE() {
		super("RCE");
	}

	public RCE(long numero, Date inicio, Date fin, int valor, Empresa empresa, int comisionATC) {
		super(numero, "RCE", inicio, fin, valor, empresa);
		this.comisionATC = comisionATC;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValorUrbanos() {
		return valorUrbanos;
	}

	public void setValorUrbanos(int valorUrbanos) {
		this.valorUrbanos = valorUrbanos;
	}

	public double getComisionATC() {
		return comisionATC;
	}

	public void setComisionATC(double comisionATC) {
		this.comisionATC = comisionATC;
	}

}

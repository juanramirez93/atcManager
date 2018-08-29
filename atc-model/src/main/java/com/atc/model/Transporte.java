package com.atc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSPORTE")
public class Transporte extends Poliza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_TRANSPORTE")
	private int id;
	private int comisionATC;

	public Transporte() {
		super("Transporte");
	}

	public Transporte(long numero, Date inicio, Date fin, int valor, Empresa empresa, int comisionATC) {
		super(numero, "Transporte", inicio, fin, valor, empresa);
		this.comisionATC = comisionATC;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComisionATC() {
		return comisionATC;
	}

	public void setComisionATC(int comisionATC) {
		this.comisionATC = comisionATC;
	}

}

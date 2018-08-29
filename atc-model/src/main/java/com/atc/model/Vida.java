package com.atc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VIDA")
public class Vida extends Poliza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_VIDA")
	private int id;
	private int valorUrbanos;

	public Vida() {
		super("Vida");
	}

	public Vida(long numero, Date inicio, Date fin, int valor, Empresa empresa) {
		super(numero, "Vida", inicio, fin, valor, empresa);
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

}

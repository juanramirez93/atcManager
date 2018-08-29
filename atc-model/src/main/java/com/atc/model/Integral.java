package com.atc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTEGRAL")
public class Integral extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_INTEGRAL")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ID_RCE")
	private RCE rce;
	@ManyToOne
	@JoinColumn(name = "ID_VIDA")
	private Vida vida;

	private int valorVentaVida;
	private int valorVentaUrbanosVida;

	public Integral() {
	}

	public Integral(String modalidad, int urbanos, Empresa empresa, RCE rce, Vida vida) {
		super(modalidad, urbanos, empresa, "Integral");
		this.rce = rce;
		this.vida = vida;
	}

	public RCE getRce() {
		return rce;
	}

	public void setRce(RCE rce) {
		this.rce = rce;
	}

	public Vida getVida() {
		return vida;
	}

	public void setVida(Vida vida) {
		this.vida = vida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValorVentaVida() {
		return valorVentaVida;
	}

	public void setValorVentaVida(int valorVentaVida) {
		this.valorVentaVida = valorVentaVida;
	}

	public int getValorVentaUrbanosVida() {
		return valorVentaUrbanosVida;
	}

	public void setValorVentaUrbanosVida(int valorVentaUrbanosVida) {
		this.valorVentaUrbanosVida = valorVentaUrbanosVida;
	}

}

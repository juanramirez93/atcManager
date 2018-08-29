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
@Table(name = "AAA")
public class AAA extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_AAA")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ID_RCE")
	private RCE rce;
	@ManyToOne
	@JoinColumn(name = "ID_TRANSPORTE")
	private Transporte transporte;
	@ManyToOne
	@JoinColumn(name = "ID_VIDA")
	private Vida vida;
	private int valorVentaVida;
	private int valorVentaUrbanosVida;

	public AAA() {
	}

	public AAA(String modalidad, int urbanos, Empresa empresa, RCE rce, Transporte transporte, Vida vida) {
		super(modalidad, urbanos, empresa, "AAA");
		this.rce = rce;
		this.transporte = transporte;
		this.vida = vida;
	}

	public RCE getRce() {
		return rce;
	}

	public void setRce(RCE rce) {
		this.rce = rce;
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
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

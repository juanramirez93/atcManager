
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
@Table(name = "CAMIONERA")
public class Camionera extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_CAMIONERA")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ID_VIDA")
	private Vida vida;

	private int valorVentaVida;
	private int valorVentaUrbanosVida;

	public Camionera() {
	}

	public Camionera(String modalidad, int urbanos, Empresa empresa, Vida vida) {
		super(modalidad, urbanos, empresa, "Camionera");
		this.vida = vida;
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

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
@Table(name = "RCEProduct")
public class RCEProduct extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@Column(name = "ID_RCEProduct")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ID_RCE")
	private RCE rce;

	public RCEProduct() {
	}

	public RCEProduct(String modalidad, int urbanos, Empresa empresa, RCE rce) {
		super(modalidad, urbanos, empresa, "RCE");
		this.rce = rce;
	}

	public RCE getRce() {
		return rce;
	}

	public void setRce(RCE rce) {
		this.rce = rce;
	}

}

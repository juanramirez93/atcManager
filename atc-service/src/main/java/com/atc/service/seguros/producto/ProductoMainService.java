package com.atc.service.seguros.producto;

import java.util.List;

import com.atc.connection.ProductoEM;
import com.atc.model.Producto;

public class ProductoMainService {

	ProductoEM em;
	
	public ProductoMainService() {
		em = new ProductoEM();
	}
	
	public List<Producto> getAllPolizas() {
		return em.getAll();
	}

}

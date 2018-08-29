package com.atc.service.seguros.producto;

import java.util.List;

import com.atc.connection.ProductoEM;
import com.atc.model.Producto;

public class SearchProductoService {

	ProductoEM em;

	public SearchProductoService() {
		em = new ProductoEM();
	}

	public List<Producto> search(String str) {
		return em.search(str);
	}

}

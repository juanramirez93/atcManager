package com.atc.service.seguros.movimiento;

import java.util.List;

import com.atc.connection.MovimientoEM;
import com.atc.connection.ProductoEM;
import com.atc.model.Movimiento;
import com.atc.model.Producto;

public class EditMovimientoService {
	MovimientoEM movimientoEM;
	ProductoEM productoEM;

	public EditMovimientoService() {
		movimientoEM = new MovimientoEM();
		productoEM = new ProductoEM();
	}
	
	public void updateMovimiento(Movimiento movimiento) {
		movimientoEM.update(movimiento);
	}
	
	public Producto[] getAllProductos() {
		List<Producto> prod = productoEM.getAll();
		Producto[] prodArray = new Producto[prod.size()];
		prodArray = prod.toArray(prodArray);
		return prodArray;
	}
}

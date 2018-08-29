package com.atc.service.seguros.movimiento;

import java.util.List;

import com.atc.connection.MovimientoEM;
import com.atc.connection.ProductoEM;
import com.atc.connection.StockEM;
import com.atc.model.Movimiento;
import com.atc.model.Producto;

public class AddMovimientoService {
	MovimientoEM movimientoEM;
	ProductoEM productoEM;
	StockEM stockEM;

	public AddMovimientoService() {
		movimientoEM = new MovimientoEM();
		productoEM = new ProductoEM();
		stockEM = new StockEM();
	}

	public void addMovimiento(Movimiento movimiento) {
		movimientoEM.save(movimiento);
	}

	public Producto[] getAllProductos() {
		List<Producto> prod = productoEM.getAll();
		Producto[] prodArray = new Producto[prod.size()];
		prodArray = prod.toArray(prodArray);
		return prodArray;
	}

	public String calcularNumeracion(Producto selectedItem, int cantidad) {
		return stockEM.verNumeracion(selectedItem.getTipo(), cantidad);
	}

	public void setStockNew(Producto selectedItem, int cantidad) {
		stockEM.setNumeracion(selectedItem.getTipo(), cantidad);
	}

}

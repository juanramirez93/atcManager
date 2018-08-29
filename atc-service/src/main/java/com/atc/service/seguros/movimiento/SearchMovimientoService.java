package com.atc.service.seguros.movimiento;

import java.util.List;

import com.atc.connection.MovimientoEM;
import com.atc.model.Movimiento;

public class SearchMovimientoService {

	MovimientoEM movimientoEM;

	public SearchMovimientoService() {
		movimientoEM = new MovimientoEM();
	}

	public List<Movimiento> search(String str) {
		return movimientoEM.search(str);
	}

}

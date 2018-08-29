package com.atc.service.afiliacion;

import java.util.List;

import com.atc.connection.AfiliacionEM;
import com.atc.model.Afiliacion;

public class MainAfiliadoService {

	AfiliacionEM afiliacionEM;

	public MainAfiliadoService() {
		afiliacionEM = new AfiliacionEM();
	}

	public List<Afiliacion> getAllAfiliaciones() {
		return this.afiliacionEM.getAll();
	}

	public List<Afiliacion> getProximosAVencer() {
				return this.afiliacionEM.getProximosAVencer();
	}

}

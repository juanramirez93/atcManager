package com.atc.service.afiliacion;

import java.util.List;

import com.atc.connection.AfiliacionEM;
import com.atc.model.Afiliacion;

public class SearchAfiliadoService {

	AfiliacionEM afiliacionEM;

	public SearchAfiliadoService() {
		afiliacionEM = new AfiliacionEM();
	}

	public List<Afiliacion> search(String str) {
		if(str.isEmpty()) {
			return afiliacionEM.getAll();
		}
		return afiliacionEM.search(str);
	}


}

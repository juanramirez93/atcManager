package com.atc.service.seguros.poliza;

import java.util.List;

import com.atc.connection.PolizaEM;
import com.atc.model.Poliza;

public class SearchPolizaService {

	PolizaEM em;

	public SearchPolizaService() {
		em = new PolizaEM();
	}

	public List<Poliza> search(String str) {
		return em.search(str);
	}
}

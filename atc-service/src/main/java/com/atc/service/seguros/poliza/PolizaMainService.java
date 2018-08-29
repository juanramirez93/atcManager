package com.atc.service.seguros.poliza;

import java.util.List;

import com.atc.connection.PolizaEM;
import com.atc.model.Poliza;

public class PolizaMainService {
	
	PolizaEM em;
	
	public PolizaMainService() {
		em = new PolizaEM();
	}
	
	public List<Poliza> getAllPolizas(){
		return this.em.getAll();
	}

}

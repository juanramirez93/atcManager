package com.atc.service.seguros.poliza;

import com.atc.connection.PolizaEM;
import com.atc.model.Poliza;

public class AddPolizaService {

	PolizaEM em;
	
	public AddPolizaService() {
		em = new PolizaEM();
	}
	
	public void addPoliza(Poliza poliza){
		em.save(poliza);
	}
	
	public boolean existPoliza(long numero){
		return em.exist(numero);
	}
	
}

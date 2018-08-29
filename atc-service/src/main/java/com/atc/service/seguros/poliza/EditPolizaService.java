package com.atc.service.seguros.poliza;

import com.atc.connection.PolizaEM;
import com.atc.model.Poliza;

public class EditPolizaService {

	PolizaEM em;
	
	public EditPolizaService() {
		em = new PolizaEM();
	}
	
	public void updatePoliza(Poliza poliza){
		em.update(poliza);
	}
	
	public boolean existPoliza(long numero){
		return em.exist(numero);
	}
	
}

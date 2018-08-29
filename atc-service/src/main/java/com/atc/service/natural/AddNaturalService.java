package com.atc.service.natural;

import com.atc.connection.NaturalEM;
import com.atc.model.Natural;

public class AddNaturalService {

	NaturalEM naturalEM;

	public AddNaturalService() {
		naturalEM = new NaturalEM();
	}

	public void addNatural(Natural natural) {
		naturalEM.save(natural);
	}

	public boolean existNatural(long cedula) {
		return naturalEM.exist(cedula);
	}
}

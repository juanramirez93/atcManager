package com.atc.service.natural;

import com.atc.connection.NaturalEM;
import com.atc.model.Natural;

public class EditNaturalService {
	NaturalEM naturalEM;

	public EditNaturalService() {
		naturalEM = new NaturalEM();
	}

	public void addEmpresa(Natural natural) {
		naturalEM.save(natural);
	}

	public boolean existNatural(long cedula) {
		return naturalEM.exist(cedula);
	}

	public void updateNatural(Natural natural) {
		naturalEM.update(natural);
	}
}

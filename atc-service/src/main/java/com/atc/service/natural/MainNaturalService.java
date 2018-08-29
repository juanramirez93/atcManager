package com.atc.service.natural;

import java.util.List;

import com.atc.connection.NaturalEM;
import com.atc.model.Natural;

public class MainNaturalService {

	NaturalEM naturalEM;

	public MainNaturalService() {
		this.naturalEM = new NaturalEM();
	}

	public List<Natural> getAllNaturales() {
		return this.naturalEM.getAll();
	}

}

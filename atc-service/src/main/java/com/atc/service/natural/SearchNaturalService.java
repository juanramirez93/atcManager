package com.atc.service.natural;

import java.util.List;

import com.atc.connection.NaturalEM;
import com.atc.model.Natural;

public class SearchNaturalService {

	NaturalEM naturalEM;

	public SearchNaturalService() {
		naturalEM = new NaturalEM();
	}

	public List<Natural> search(String str) {
		return naturalEM.search(str);
	}

}

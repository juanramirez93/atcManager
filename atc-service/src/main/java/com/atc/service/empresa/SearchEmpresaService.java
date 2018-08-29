package com.atc.service.empresa;

import java.util.List;

import com.atc.connection.EmpresaEM;
import com.atc.model.Empresa;

public class SearchEmpresaService {

	EmpresaEM empresaEM;
	
	public SearchEmpresaService() {
		empresaEM = new EmpresaEM();
	}
	
	public List<Empresa> search(String str) {
		return empresaEM.search(str);
	}
	
}

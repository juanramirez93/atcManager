package com.atc.service.empresa;

import java.util.List;

import com.atc.connection.EmpresaEM;
import com.atc.model.Empresa;

public class MainEmpresaService {
	
	EmpresaEM empresaEM;
	
	public MainEmpresaService() {
		this.empresaEM = new EmpresaEM();
	}

	public List<Empresa> getAllEmpresas() {
		return this.empresaEM.getAll();
	}

}

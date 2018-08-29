package com.atc.service.empresa;

import com.atc.connection.EmpresaEM;
import com.atc.model.Empresa;

public class AddEmpresaService {
	EmpresaEM empresaEM;
	
	public AddEmpresaService() {
		empresaEM = new EmpresaEM();
	}
	
	public void addEmpresa(Empresa empresa){
		empresaEM.save(empresa);
	}
	
	public boolean existEmpresa(long nit) {
		return empresaEM.exist(nit);
	}
}

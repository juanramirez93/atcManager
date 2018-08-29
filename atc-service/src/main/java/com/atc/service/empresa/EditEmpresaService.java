package com.atc.service.empresa;

import com.atc.connection.EmpresaEM;
import com.atc.model.Empresa;

public class EditEmpresaService {
	EmpresaEM empresaEM;
	
	public EditEmpresaService() {
		empresaEM = new EmpresaEM();
	}
	
	public void addEmpresa(Empresa empresa){
		empresaEM.save(empresa);
	}
	
	public boolean existEmpresa(long nit) {
		return empresaEM.exist(nit);
	}
	
	public void updateEmpresa(Empresa empresa){
		empresaEM.update(empresa);
	}
}

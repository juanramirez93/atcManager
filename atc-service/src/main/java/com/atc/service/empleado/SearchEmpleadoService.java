package com.atc.service.empleado;

import java.util.List;

import com.atc.connection.EmpleadoEM;
import com.atc.model.Empleado;

public class SearchEmpleadoService {

EmpleadoEM empleadoEM;
	
	public SearchEmpleadoService() {
		empleadoEM = new EmpleadoEM();
	}
	
	public List<Empleado> search(String str) {
		return empleadoEM.search(str);
	}
	
}

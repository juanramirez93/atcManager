package com.atc.service.empleado;

import java.util.List;

import com.atc.connection.EmpleadoEM;
import com.atc.model.Empleado;

public class MainEmpleadoService {

EmpleadoEM empleadoEM;
	
	public MainEmpleadoService() {
		this.empleadoEM = new EmpleadoEM();
	}

	public List<Empleado> getAllEmpleados() {
		return this.empleadoEM.getAll();
	}

	

}

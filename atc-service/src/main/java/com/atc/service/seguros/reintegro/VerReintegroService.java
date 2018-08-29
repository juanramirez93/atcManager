package com.atc.service.seguros.reintegro;

import java.util.List;

import com.atc.connection.EmpleadoEM;
import com.atc.model.Empleado;

public class VerReintegroService {

	EmpleadoEM empleadoEM;

	public VerReintegroService() {
		empleadoEM = new EmpleadoEM();
	}

	public Empleado[] getAll() {
		List<Empleado> empleados = empleadoEM.getAll();
		Empleado[] empleadosArray = new Empleado[empleados.size()];
		empleadosArray = empleados.toArray(empleadosArray);
		return empleadosArray;
	}

}

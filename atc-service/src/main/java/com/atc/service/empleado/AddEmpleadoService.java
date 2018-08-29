package com.atc.service.empleado;

import com.atc.connection.EmpleadoEM;
import com.atc.model.Empleado;

public class AddEmpleadoService {
	EmpleadoEM empleadoEM;

	public AddEmpleadoService() {
		empleadoEM = new EmpleadoEM();
	}

	public void addEmpleado(Empleado empleado) {
		empleadoEM.save(empleado);
	}

	public boolean existEmpleado(long nit) {
		return empleadoEM.exist(nit);
	}
}

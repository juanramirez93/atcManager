package com.atc.service.afiliacion;

import java.util.List;

import com.atc.connection.AfiliacionEM;
import com.atc.connection.EmpleadoEM;
import com.atc.connection.PersonaEM;
import com.atc.model.Afiliacion;
import com.atc.model.Empleado;
import com.atc.model.Persona;

public class AddAfiliadoService {
	AfiliacionEM afiliacionEM;
	PersonaEM personaEM;
	EmpleadoEM empleadoEM;

	public AddAfiliadoService() {
		afiliacionEM = new AfiliacionEM();
		personaEM = new PersonaEM();
		empleadoEM = new EmpleadoEM();
	}

	public void addAfiliacion(Afiliacion afiliacion) {
		afiliacionEM.save(afiliacion);
	}

	public Persona getPersonaByIdentificacion(long identificacion) {
		return personaEM.getNaturalByIdentificaicion(identificacion);
	}

	public String[] getEmpleado() {
		List<Empleado> empleados = empleadoEM.getAll();
		String[] emp = new String[empleados.size()];
		int i = 0;
		for (Empleado em : empleados) {
			emp[i] = em.getAbreviatura();
		}
		return emp;
	}

	public void update(Afiliacion afiliacion) {
		afiliacionEM.update(afiliacion);
	}

	public boolean existAfiliado(long ident) {
		return afiliacionEM.existAfiliado(ident);
	}
}

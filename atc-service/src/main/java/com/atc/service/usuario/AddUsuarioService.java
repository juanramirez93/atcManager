package com.atc.service.usuario;

import com.atc.connection.EmpleadoEM;
import com.atc.connection.UsuarioEM;
import com.atc.model.Empleado;
import com.atc.model.Usuario;

public class AddUsuarioService {

	UsuarioEM usuarioEM;
	EmpleadoEM empleadoEM;
	
	public AddUsuarioService() {
		usuarioEM = new UsuarioEM();
		empleadoEM = new EmpleadoEM();
	}
	
	public void addUsuario(Usuario usuario){
		usuarioEM.save(usuario);
	}
	
	public boolean existUsuario(String usuario){
		return usuarioEM.exist(usuario);
	}

	public boolean existEmpleado(long cedula) {
		return empleadoEM.exist(cedula);
	}

	public String getEmpleadoStr(long cedula) {
		Empleado empleado = empleadoEM.getEmpleadoByNit(cedula);
		return empleado.getAbreviatura();
	}
	
	public Empleado getEmpleado(long cedula) {
		return empleadoEM.getEmpleadoByNit(cedula);
	}
	
}

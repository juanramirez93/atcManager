package com.atc.service.usuario;

import java.util.List;

import com.atc.connection.UsuarioEM;
import com.atc.model.Usuario;

public class UsuarioMainService {

	UsuarioEM usuarioEM;
	
	public UsuarioMainService() {
		usuarioEM = new UsuarioEM();
	}
	
	public List<Usuario> getAllUsuarios(){
		return usuarioEM.getAll();
	}
 	
}

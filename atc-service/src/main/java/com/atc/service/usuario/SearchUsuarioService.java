package com.atc.service.usuario;

import java.util.List;

import com.atc.connection.UsuarioEM;
import com.atc.model.Usuario;

public class SearchUsuarioService {

	UsuarioEM usuarioEM;

	public SearchUsuarioService() {
		usuarioEM = new UsuarioEM();
	}

	public List<Usuario> search(String str) {
		return usuarioEM.search(str);
	}
}

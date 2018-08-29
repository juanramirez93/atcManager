package com.atc.service;

import com.atc.model.Usuario;

public enum Session {

	INSTANCE;

	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}

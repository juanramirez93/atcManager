package com.atc.service.afiliacion;

import com.atc.connection.AfiliacionEM;
import com.atc.connection.PersonaEM;
import com.atc.model.Afiliacion;
import com.atc.model.Persona;

public class EditAfiliadoService {
	AfiliacionEM afiliacionEM;
	PersonaEM personaEm;

	public EditAfiliadoService() {
		afiliacionEM = new AfiliacionEM();
		personaEm = new PersonaEM();
	}
	
	public void updateAfiliacion(Afiliacion afiliacion) {
		afiliacionEM.update(afiliacion);
	}

	public Persona getPersonaByIdentificacion(long identificacion) {
		return personaEm.getNaturalByIdentificaicion(identificacion);
	}
}

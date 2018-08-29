package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Poliza;

public class PolizaEM extends EM implements AbstractEntityManager<Poliza> {

	public List<Poliza> getAll() {
		open();
		TypedQuery<Poliza> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT p FROM Poliza p ORDER BY numero ASC", Poliza.class);
		List<Poliza> polizas = query.getResultList();
		return polizas;
	}

	public void save(Poliza o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean exist(long numero) {
		for (Poliza poliza : getAll()) {
			if (poliza.getNumero() == numero) {
				return true;
			}
		}
		return false;
	}

	public List<Poliza> search(String str) {
		open();
		TypedQuery<Poliza> query = EntityManagerHandler.INSTANCE.getEntityManager().createQuery(
				"SELECT p FROM Poliza p where p.numero like '%" + str + "%' OR p.ramo like '%" + str + "%'",
				Poliza.class);
		List<Poliza> polizas = query.getResultList();
		return polizas;
	}

	public Poliza getPolizaByNumero(long numero) {
		for (Poliza poliza : getAll()) {
			if (poliza.getNumero() == numero) {
				return poliza;
			}
		}
		return null;
	}

	public void update(Poliza o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

}

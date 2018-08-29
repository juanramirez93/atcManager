package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Natural;

public class NaturalEM extends EM implements AbstractEntityManager<Natural> {

	public List<Natural> getAll() {
		open();
		TypedQuery<Natural> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Natural e ORDER BY cedula ASC", Natural.class);
		List<Natural> resultList = query.getResultList();
		return resultList;
	}

	public void save(Natural natural) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(natural);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean exist(long natural) {

		for (Natural nat : getAll()) {
			if (nat.getCedula() == natural) {
				return true;
			}
		}
		return false;
	}

	public Natural getNaturalByCC(long cedula) {
		for (Natural natu : getAll()) {
			if (natu.getCedula() == cedula) {
				return natu;
			}
		}
		return null;
	}

	public List<Natural> search(String str) {
		open();
		TypedQuery<Natural> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Natural e where e.cedula like '%" + str + "%' OR e.nombres like '%" + str
						+ "%' OR e.apellidos like '%" + str + "%'", Natural.class);
		List<Natural> natural = query.getResultList();
		return natural;
	}

	public void update(Natural natural) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(natural);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}
}

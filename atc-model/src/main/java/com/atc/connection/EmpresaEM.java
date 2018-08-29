package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Empresa;

public class EmpresaEM extends EM implements AbstractEntityManager<Empresa> {

	public List<Empresa> getAll() {
		open();
		TypedQuery<Empresa> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Empresa e ORDER BY razonSocial ASC", Empresa.class);
		List<Empresa> empresas = query.getResultList();
		return empresas;
	}

	public void save(Empresa empresa) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(empresa);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean exist(long nit) {

		for (Empresa em : getAll()) {
			if (em.getNit() == nit) {
				return true;
			}
		}
		return false;
	}

	public Empresa getEmpresaByNit(long nit) {
		for (Empresa em : getAll()) {
			if (em.getNit() == nit) {
				return em;
			}
		}
		return null;
	}

	public List<Empresa> search(String str) {
		open();
		TypedQuery<Empresa> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Empresa e where e.nit like '%" + str + "%' OR e.razonSocial like '%" + str
						+ "%' OR e.siglas like '%" + str + "%'", Empresa.class);
		List<Empresa> empresa = query.getResultList();
		return empresa;
	}

	public void update(Empresa empresa) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(empresa);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}
}

package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Empleado;

public class EmpleadoEM extends EM implements AbstractEntityManager<Empleado> {

	public List<Empleado> getAll() {
		open();
		TypedQuery<Empleado> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Empleado e ORDER BY cedula ASC", Empleado.class);
		List<Empleado> empleado = query.getResultList();
		return empleado;
	}

	public void save(Empleado empleado) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(empleado);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean exist(long cedula) {

		for (Empleado em : getAll()) {
			if (em.getCedula() == cedula) {
				return true;
			}
		}
		return false;
	}

	public Empleado getEmpleadoByNit(long cedula) {
		for (Empleado em : getAll()) {
			if (em.getCedula() == cedula) {
				return em;
			}
		}
		return null;
	}

	public List<Empleado> search(String str) {
		open();
		TypedQuery<Empleado> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT e FROM Empleado e where e.cedula like '%" + str + "%' OR e.nombres like '%" + str
						+ "%' OR e.apellidos like '%" + str + "%'", Empleado.class);
		List<Empleado> empleado = query.getResultList();
		return empleado;
	}

	public void update(Empleado empleado) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(empleado);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}
}

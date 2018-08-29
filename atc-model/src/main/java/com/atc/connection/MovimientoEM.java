package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Movimiento;

public class MovimientoEM extends EM implements AbstractEntityManager<Movimiento> {

	public List<Movimiento> getAll() {
		open();
		TypedQuery<Movimiento> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT m FROM Movimiento m ORDER BY fecha DESC, remRCE DESC", Movimiento.class);
		List<Movimiento> movimientos = query.getResultList();
		return movimientos;
	}

	public void save(Movimiento movimientos) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(movimientos);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public List<Movimiento> search(String str) {
		open();
		TypedQuery<Movimiento> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT m FROM Movimiento m where m.producto.empresa.siglas like '%" + str
						+ "%' OR m.producto.empresa.razonSocial like '%" + str + "%' OR m.producto.empresa.nit like '%"
						+ str + "%' OR m.numeracion like '%" + str + "%' OR m.remRCE like '%" + str
						+ "%' OR m.remTransporte like '%" + str + "%' OR m.factVida like '%" + str
						+ "%' OR m.factAllianzRCE like '%" + str + "%' OR m.factAllianzTransporte like '%" + str
						+ "%' OR m.reciboPago like '%" + str + "%'", Movimiento.class);
		List<Movimiento> movimientos = query.getResultList();
		return movimientos;
	}

	public void update(Movimiento movimiento) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(movimiento);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}
}

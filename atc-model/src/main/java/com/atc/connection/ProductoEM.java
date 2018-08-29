package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Producto;

public class ProductoEM extends EM implements AbstractEntityManager<Producto> {

	public List<Producto> getAll() {
		open();
		TypedQuery<Producto> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT p FROM Producto p ORDER BY p.empresa.nombre ASC", Producto.class);
		List<Producto> empresas = query.getResultList();
		return empresas;
	}

	public void save(Producto producto) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(producto);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public List<Producto> search(String str) {
		open();
		TypedQuery<Producto> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery(
						"SELECT p FROM Producto p where p.empresa.nit like '%" + str
								+ "%' OR p.empresa.razonSocial like '%" + str + "%' OR p.empresa.siglas like '%" + str
								+ "%' OR p.modalidad like '%" + str + "%' OR p.tipo like '%" + str + "%'",
						Producto.class);
		List<Producto> productos = query.getResultList();
		return productos;
	}

	public void update(Producto o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

}

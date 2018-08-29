package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Usuario;

public class UsuarioEM extends EM implements AbstractEntityManager<Usuario> {

	public List<Usuario> getAll() {
		open();
		TypedQuery<Usuario> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT u FROM Usuario u ORDER BY user ASC", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}

	public void save(Usuario usuario) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(usuario);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean exist(String user) {
		for (Usuario us : getAll()) {
			if (us.getUser() == user) {
				return true;
			}
		}
		return false;
	}

	public Usuario getUsuarioByUser(String user) {
		for (Usuario us : getAll()) {
			if (us.getUser().equals(user)) {
				return us;
			}
		}
		return null;
	}

	public List<Usuario> search(String str) {
		open();
		TypedQuery<Usuario> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT u FROM Usuario u where u.user like '%" + str + "%' OR u.empleado.nombres like '%"
						+ str + "%' OR u.empleado.abreviatura like '%" + str + "%'", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}

	public void update(Usuario o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}
}

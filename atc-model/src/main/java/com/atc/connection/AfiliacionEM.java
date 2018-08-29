package com.atc.connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Afiliacion;

public class AfiliacionEM extends EM implements AbstractEntityManager<Afiliacion> {

	public List<Afiliacion> getAll() {
		open();
		TypedQuery<Afiliacion> query = EntityManagerHandler.INSTANCE.getEntityManager().createQuery(
				"SELECT a FROM Afiliacion a WHERE a.afiliado.tipo = 'Natural' ORDER BY a.ultimoPago DESC ",
				Afiliacion.class);
		List<Afiliacion> afiliaciones = query.getResultList();
		return afiliaciones;
	}

	public List<Afiliacion> search(String str) {
		open();
		TypedQuery<Afiliacion> query = EntityManagerHandler.INSTANCE.getEntityManager().createQuery(
				"SELECT a FROM Afiliacion a WHERE a.afiliado.identificacion like '%"
						+ str + "%' OR a.afiliado.nombre like '%" + str + "%' ORDER BY a.fechaAfiliacion DESC",
				Afiliacion.class);
		List<Afiliacion> afiliaciones = query.getResultList();
		return afiliaciones;
	}

	public void save(Afiliacion o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public void update(Afiliacion o) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(o);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public boolean existAfiliado(long ident) {
		for (Afiliacion afiliacion : getAll()) {
			if (afiliacion.getAfiliado().getIdentificacion() == ident) {
				return true;
			}
		}
		return false;
	}

	public List<Afiliacion> getProximosAVencer() {
		List<Afiliacion> retur = new ArrayList<Afiliacion>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		for(Afiliacion af : getAll()) {
			if(af.getUltimoPago()!= null && af.getUltimoPago().after(new Date())&& af.getUltimoPago().before(calendar.getTime())) {
				retur.add(af);
			}
		}
		
		return retur;
	}

	
	
}

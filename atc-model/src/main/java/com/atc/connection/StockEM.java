package com.atc.connection;

import java.util.List;

import javax.persistence.TypedQuery;

import com.atc.model.Stock;

public class StockEM extends EM implements AbstractEntityManager<Stock> {

	public List<Stock> getAll() {
		open();
		TypedQuery<Stock> query = EntityManagerHandler.INSTANCE.getEntityManager()
				.createQuery("SELECT s FROM Stock s ORDER BY fecha ASC", Stock.class);
		List<Stock> stock = query.getResultList();
		return stock;
	}

	public void save(Stock stock) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(stock);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public void update(Stock stock) {
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().merge(stock);
		EntityManagerHandler.INSTANCE.getEntityManager().getTransaction().commit();
	}

	public void setNumeracion(String tipo, int cantidad) {
		open();
		System.out.println(tipo + cantidad);
		TypedQuery<Stock> query = EntityManagerHandler.INSTANCE.getEntityManager().createQuery(
				"SELECT s FROM Stock s WHERE producto = '" + tipo + "' AND restantes > 0 ORDER BY inicio ASC",
				Stock.class);
		List<Stock> stock = query.getResultList();
		int restantes = cantidad;
		int i = 0;
		while (restantes > 0) {
			Stock st = stock.get(i);
			int existencia = st.getRestantes();
			if (existencia > restantes) {
				int aux = st.getProximo();
				st.setProximo(aux + restantes);
				st.setRestantes(st.getFin() - st.getProximo() + 1);
				restantes = 0;
				update(st);
			} else {
				restantes = restantes - st.getRestantes();
				st.setProximo(st.getFin());
				st.setRestantes(0);
				update(st);
			}
			i++;
		}
	}

	public String verNumeracion(String tipo, int cantidad) {
		open();
		System.out.println(tipo + cantidad);
		TypedQuery<Stock> query = EntityManagerHandler.INSTANCE.getEntityManager().createQuery(
				"SELECT s FROM Stock s WHERE producto = '" + tipo + "' AND restantes > 0 ORDER BY inicio ASC",
				Stock.class);
		List<Stock> stock = query.getResultList();
		if (stock.isEmpty()) {
			return "No hay existencia";
		}
		int restantes = cantidad;
		int i = 0;
		String numeracion = "";
		while (restantes > 0 && i < stock.size()) {
			Stock st = stock.get(i);
			int existencia = st.getRestantes();
			if (existencia >= restantes) {
				numeracion += st.getProximo() + " - " + (st.getProximo() + restantes - 1);
				restantes = 0;
			} else {
				numeracion += st.getProximo() + " - " + st.getFin() + "// ";
				restantes = restantes - st.getRestantes();
			}
			i++;
		}
		if (restantes == 0) {
			return numeracion;
		} else {
			return "No hay existencia";
		}
	}

	public List<Stock> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}
}

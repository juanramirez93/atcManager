package com.atc.connection;

import java.util.List;

public interface AbstractEntityManager<T> {

	public List<T> getAll();
	
	public List<T> search(String str);

	public void save(T o);

	public void update(T o);
}

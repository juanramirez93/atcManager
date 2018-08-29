package com.atc.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum EntityManagerHandler {

	INSTANCE;
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atc");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void open() {
		if( !entityTransaction.isActive() ){
			entityTransaction.begin();
		}
	}
	
	public EntityTransaction getMyTransaction() {
		return entityTransaction;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
	
	public void shutdown(){
		entityManager.close();
		entityManagerFactory.close();
	}
}

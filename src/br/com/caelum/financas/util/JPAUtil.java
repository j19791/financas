package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	// static garante a criaçãod e apenas um objeto fabrica na memoria
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("financas");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}

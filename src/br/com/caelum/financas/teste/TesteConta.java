package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("Joao Ferreira");
		conta.setBanco("HSBC");
		conta.setNumero("123345");
		conta.setAgencia("321");
		// estado: Transient - conta n�o existe no bd. Criada pela aplica��o e
		// at� a chamada do m�todo persist() ela n�o seria salva no bd e sumiria
		// se a aplica��o terminasse.

		// carregar a configura��o (persistence.xml)
		// criar uma EntityManagerFactory baseada na unidade de persistencia
		// financas
		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("financas");

		// A f�brica por sua vez cria um EntityManager
		// EntityManager manager = entityManagerFactory.createEntityManager();

		// utilizando a JAPUTIL
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();// cuida da transacao

		manager.persist(conta);// conta no estado managed - sincronizada no bd

		conta.setTitular("Mario Santos");// estado managed - sera sincronizado
											// (update) no bd

		manager.getTransaction().commit();

		manager.close(); // estado managed termina

		EntityManager manager2 = new JPAUtil().getEntityManager();

		manager2.getTransaction().begin();

		// Detached - entidade n�o � mais Transient, pois mesmo que a aplica��o
		// termine, a
		// conta com a ID 1 continua existindo no banco - foi Managed antes.
		// Aten��o: os dados entre o objeto e o registro no banco divergem.
		conta.setId(1);
		conta.setNumero("54321");
		conta.setAgencia("3344");

		// PersistentObjectException - detached n�o pode ser usado com persit
		// manager2.persist(conta);

		manager2.merge(conta);// detached -> managed

		manager2.getTransaction().commit();

		manager2.close();

		EntityManager manager3 = new JPAUtil().getEntityManager();

		manager3.getTransaction().begin();

		Conta conta2 = manager3.find(Conta.class, 1); // conta managed

		manager3.remove(conta2);// para remover o objeto ele precisa ser managed

		manager3.getTransaction().commit();

		manager3.close();

	}

}

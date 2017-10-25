package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaContaCliente {

	public static void main(String[] args) {

		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo");
		cliente.setEndereco("Rua Fulano, 123");
		cliente.setProfissao("Professor");

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Douglas");
		cliente2.setEndereco("Rua Fulano, 234");
		cliente2.setProfissao("Professor");

		Conta conta = new Conta();
		conta.setId(2); // essa conta está no estado 'Detached' pois já possui
						// um id!!

		cliente.setConta(conta);
		cliente2.setConta(conta);// incluindo a violação

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(cliente);
		em.persist(cliente2); // nao devera funcionar pois ira ter violacao de
								// integridade: 1 conta apenas para 1 cliente

		em.getTransaction().commit();

	}

}

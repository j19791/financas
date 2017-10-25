package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteEstadosJPA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager manager = new JPAUtil().getEntityManager();

		// carregar uma entidade por sua chave primária
		Conta conta = manager.find(Conta.class, 1);// find devolveu um conta
													// gerenciada(ou Managed)
													// pelo JPA. Neste estado é
													// garantido que o objeto
													// terá sua representação
													// idêntica no banco.

		conta.setTitular("Pedro Ferreira");// JPA automaticamente sincronizou os
											// dados da conta com o registro na
											// tabela.

		System.out.println(conta.getTitular());

		manager.close();

	}

}

package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacoesConta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager manager = new JPAUtil().getEntityManager();

		// utilizando comportamento lazy loading - preguiçoso (padrão) para o
		// relacionamento para-muitos
		Query queryLazy = manager.createQuery("select c from Conta c");

		List<Conta> contas = queryLazy.getResultList();

		for (Conta conta : contas) {
			System.out.println(conta.getMovimentacoes());// N+1 : comportamento
															// lazy - só usa qdo
															// precisar - 1
															// select p/ as
															// contas e 1 select
															// na tabela
															// movimentações p/
															// cada conta
		}

		Query queryEager = manager
				.createQuery("select c from Conta c join fetch c.movimentacoes");// eager
																					// -
																					// ansioso

		List<Conta> contas2 = queryEager.getResultList();

		for (Conta conta : contas2) {
			System.out.println(conta.getMovimentacoes());// eager faz apenas 1
															// select
		}

		Query queryDistinct = manager
				.createQuery("select distinct c from Conta c join fetch c.movimentacoes");

		List<Conta> contas3 = queryDistinct.getResultList();

		for (Conta conta : contas3) {
			System.out.println(conta.getTitular());
			System.out.println(conta.getMovimentacoes());
		}

		Query queryLeftJoin = manager
				.createQuery("select distinct c from Conta c left join fetch c.movimentacoes");// eager
		// -
		// ansioso

		List<Conta> contas4 = queryLeftJoin.getResultList();

		for (Conta conta : contas4) {
			System.out.println(conta.getTitular());
			System.out.println(conta.getMovimentacoes());// left join - aparece
															// todas as contas
															// sem movimentacoes
															// inclusive
		}

		manager.close();

	}

}

package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager manager = new JPAUtil().getEntityManager();

		Movimentacao movimentacao = manager.find(Movimentacao.class, 2);

		System.out.println("Titular da conta: "
				+ movimentacao.getConta().getTitular());// pegando o titular da
														// conta da movimentacao
														// 2
		/*
		 * Conta conta = manager.find(Conta.class, 2);
		 * System.out.println(conta.getMovimentacoes().size());// conta com id 2
		 * // tem n // movimentacaoes
		 */

		Query query = manager.createQuery("select c from Conta c");

		List<Conta> contas = query.getResultList();

		for (Conta conta : contas) {
			System.out.println("Número de movimentações ...: "
					+ conta.getMovimentacoes().size());
		}

	}

}

package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteInserirMovimentacao {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		// Conta conta = em.find(Conta.class, 1);
		Query query = em.createQuery("select c from Conta c");

		List<Conta> contas = query.getResultList();

		for (Conta conta2 : contas) {
			List<Movimentacao> movimentacoes = conta2.getMovimentacoes();
			for (Movimentacao movimentacao : movimentacoes) {
				System.out.println("Movimentação: "
						+ movimentacao.getDescricao());
			}
		}

		em.close();

	}
}

package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaConsulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(2);

		/*
		 * Query query = manager
		 * .createQuery("select m from Movimentacao m where m.conta.id=" +
		 * conta.getId());// o alvo da consulta não é a tabela no // banco mas
		 * sim a classe // Movimentacao do nosso modelo OO.
		 */

		/*
		 * Query query = manager
		 * .createQuery("select m from Movimentacao m where m.conta=?1"); //
		 * utilizando // Positional // Parameter // Notation
		 * 
		 * query.setParameter(1, conta);// passando a conta como parametro
		 */

		// utilizando Named Parameters
		Query query = manager
				.createQuery("select m from Movimentacao m where m.conta=:pConta"
						+ " and m.tipoMovimentacao = :pTipo"
						+ " order by m.valor desc");

		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
		}
	}

}

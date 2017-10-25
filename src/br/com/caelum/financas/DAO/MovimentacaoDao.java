package br.com.caelum.financas.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

	private EntityManager manager;

	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Double mediaDaContaPeloTipo(Conta conta, TipoMovimentacao saida) {
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta=:pConta "
				+ "and m.tipoMovimentacao=:pTipo";

		// avg trabalha com Double
		TypedQuery<Double> queryDouble = manager
				.createQuery(jpql, Double.class);

		queryDouble.setParameter("pConta", conta);
		queryDouble.setParameter("pTipo", TipoMovimentacao.SAIDA);

		return queryDouble.getSingleResult();
	}

}

package br.com.caelum.financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.DAO.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncoes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(2);

		// utilizando função de agregação soma
		Query query = manager
				.createQuery("select sum(m.valor) from Movimentacao m where m.conta=:pConta"
						+ " and m.tipoMovimentacao = :pTipo"
						+ " order by m.valor desc");

		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		// a query só vai retornar um resultado (sum) - utilizar
		// getSingleResult()
		BigDecimal resultado = (BigDecimal) query.getSingleResult();

		System.out.println("Total movimentado ..: R$ " + resultado);

		Query queryMax = manager
				.createQuery("select max(m.valor) from Movimentacao m where m.conta = :pConta");

		queryMax.setParameter("pConta", conta);

		BigDecimal max = (BigDecimal) queryMax.getSingleResult();

		System.out.println("Maximo movimentado ..: R$ " + max);

		Query queryCount = manager
				.createQuery("select count(m) from Movimentacao m where m.conta = :pConta");

		queryCount.setParameter("pConta", conta);
		Long quantidade = (Long) queryCount.getSingleResult();
		System.out.println("Contagem de movimentações: " + quantidade);

		String jpql = "select sum(m.valor) from Movimentacao m where m.conta=:pConta"
				+ " and m.tipoMovimentacao = :pTipo" + " order by m.valor desc";

		System.out.println("Total movimentado ..: R$ " + resultado);

		TypedQuery<BigDecimal> queryTyped = manager.createQuery(jpql,
				BigDecimal.class);

		queryTyped.setParameter("pConta", conta);
		queryTyped.setParameter("pTipo", TipoMovimentacao.SAIDA);

		BigDecimal resultadoQueryTyped = queryTyped.getSingleResult();

		System.out.println("Total movimentado com TypedQuery ..: R$ "
				+ resultadoQueryTyped);

		// utilizando MovimentacaoDao
		MovimentacaoDao dao = new MovimentacaoDao(manager);

		/*
		 * jpql =
		 * "select avg(m.valor) from Movimentacao m where m.conta=:pConta " +
		 * "and m.tipoMovimentacao=:pTipo";
		 * 
		 * // avg trabalha com Double TypedQuery<Double> queryDouble = manager
		 * .createQuery(jpql, Double.class);
		 * 
		 * queryDouble.setParameter("pConta", conta);
		 * queryDouble.setParameter("pTipo", TipoMovimentacao.SAIDA);
		 * 
		 * Double resultadoDouble = queryDouble.getSingleResult();
		 */

		Double resultadoDouble = dao.mediaDaContaPeloTipo(conta,
				TipoMovimentacao.SAIDA);

		System.out.println("Media movimentado com queryDouble..: R$ "
				+ resultadoDouble);

		// utilizando namedQueryes
		TypedQuery<Double> queryNamedQuery = manager.createNamedQuery(
				"mediaDaContaPeloTipoMovimentacao", Double.class);

		queryNamedQuery.setParameter("pConta", conta);
		queryNamedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);

		Double NamedQuery = queryNamedQuery.getSingleResult();

		System.out.println("Media movimentado com NamedQuery..: R$ "
				+ NamedQuery);
	}

}

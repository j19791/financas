package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Categoria categoria = new Categoria();
		categoria.setId(3);

		// fazendo join de duas tabelas com relacionamento manytomany
		Query query = manager
				.createQuery("select m from Movimentacao m join m.categorias c where c = :pCategoria");

		query.setParameter("pCategoria", categoria);

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
		}

	}

}

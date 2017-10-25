package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRelacionamento2 {

	public static void main(String[] args) {

		Conta conta = new Conta();

		// transient
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());// data atual
		movimentacao.setDescricao("Conta de luz");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);// enum
		movimentacao.setValor(new BigDecimal("123.9"));

		conta.setId(2); // conta detached - conta com id 2 ja foi menaged e
						// sincronizado com bd
		movimentacao.setConta(conta);

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(movimentacao);// managed

		manager.getTransaction().commit();
		manager.close();
	}
}
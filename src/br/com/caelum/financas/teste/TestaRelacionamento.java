package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRelacionamento {

	public static void main(String[] args) {

		// transient
		Conta conta = new Conta();
		conta.setTitular("Ana Maria");
		conta.setBanco("Itau");
		conta.setNumero("54321");
		conta.setAgencia("111");

		// transient
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());// data atual
		movimentacao.setDescricao("Conta de luz");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);// enum
		movimentacao.setValor(new BigDecimal("123.9"));

		movimentacao.setConta(conta);

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(conta);// managed - a conta precisa ser pesistida
								// primeiro p/ ser usada na movimentacao
		manager.persist(movimentacao);// managed

		manager.getTransaction().commit();
		manager.close();
	}
}
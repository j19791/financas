package br.com.caelum.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente<endereco> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;

	@JoinColumn(unique = true)
	// garante restrição - 1 conta para 1 cliente - mas só funciona se recriar o
	// esquema da base
	@OneToOne
	private Conta conta;
	private String endereco;
	private String profissao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;

	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;

	}
}
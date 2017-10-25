package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
// Entidades (ou Entities) são as classes que tem uma tabela associada.
public class Conta {

	@Id
	// obrigatório declarar o atributo que representa a chave primária com @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// indicamos que o banco deve atribuir o valor da chave, e não a aplicação.
	// Ao inserir uma conta no banco, automaticamente será alocada uma ID.
	private Integer id;// chave primaria. boa prática usar chaves auxiliares e
						// simples a favor de chaves compostas.
	private String titular;
	private String banco;
	private String agencia;
	private String numero;

	@OneToMany(mappedBy = "conta")
	// Declarando o relacionamento conta X movimentacao (forte- aonde tem a
	// chave estrangeira) como bidirecional
	private List<Movimentacao> movimentacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Movimentacao> getMovimentacoes() {
		// TODO Auto-generated method stub
		return movimentacoes;
	}

}

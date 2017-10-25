package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name = "mediaDaContaPeloTipoMovimentacao", query = "select avg(m.valor) from Movimentacao m where m.conta=:pConta  and m.tipoMovimentacao = :pTipo")
@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;// o double não tem a precisão suficiente. Não deve
								// ser utilizado para guardar valores
								// financeiros.

	@Enumerated(EnumType.STRING)
	// queremos mapear no bd como sendo do tipo String. Definido o parâmetro
	// EnumType p/ STRING. No registro do bd ficará gravado o varchar: SAIDA ou
	// ENTRADA.
	private TipoMovimentacao tipoMovimentacao;

	@Temporal(TemporalType.DATE)
	// precisao
	// DATE: somente a data, sem a hora;
	// TIME: somente a hora, sem data;
	// TIMESTAMP: tanto data quanto hora.
	private Calendar data;

	private String descricao;

	@ManyToOne
	// cardinalidade da Movimentacao em relação à Conta
	private Conta conta; // mapear um relacionamento entre as entidades, que no
							// banco deverá ser refletido por uma chave
							// estrangeira (Foreign Key). (conta_id)

	@ManyToMany
	public List<Categoria> categorias;

	// criara a tabela de relacionamentos

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

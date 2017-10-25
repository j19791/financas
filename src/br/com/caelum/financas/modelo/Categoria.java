package br.com.caelum.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;

	@Deprecated
	// esse construtor não deveria ser usado no nosso projeto.
	public Categoria() { // construtor padrão p/ ser usado pelo JPA
	}

	public Categoria(String categoria) {
		// TODO Auto-generated constructor stub
		this.nome = categoria;
	}

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
}
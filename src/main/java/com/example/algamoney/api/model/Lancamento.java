package com.example.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity // Anotação para indicar que a classe é uma entidade JPA (mapeamento para tabela do banco de dados)
@Table(name = "lancamento") // Isso serve para nomear a tabela no banco de dados, evitando conflitos com palavras reservadas.
public class Lancamento {

	@Id // Anotação para indicar que o campo é a chave primária da tabela 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automáticade valor da chave primária 
	// (auto-incremento)
	private Long codigo;
	
	@NotNull // Anotação para indicar que o campo não pode ser nulo
	private String descricao;

	@NotNull
	@Column(name = "data_vencimento") // nomeia a coluna no banco de dados.
	private LocalDate dataVencimento;

	@Column(name = "data_pagamento") // nomeia a coluna no banco de dados.
	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor; // Campo para armazenar o valor do lançamento,
	//  usando BigDecimal para precisão em valores monetários

	private String observacao;

	@NotNull
	@Enumerated(EnumType.STRING) // Anotação para indicar que o campo é um enum e deve ser armazenado
	// como string no banco de dados (em vez de ordinal), para facilitar a leitura e manutenção dos dados. 
	// enum é um tipo de dado que representa um conjunto fixo de constantes, 
	// nesse casso, os tipos de lançamento (RECEITA ou DESPESA). São fixos e não podem ser alterados,
	//  garantindo a integridade dos dados.
	private TipoLancamento tipo;

	@NotNull
	@ManyToOne // Anotação para indicar que o campo é uma relação Many-to-One
	//(muitos lançamentos podem estar associados a uma categoria, mas cada lançamento tem apenas uma categoria)
	@JoinColumn(name = "codigo_categoria") // Anotação para nomear a coluna de chave estrangeira no banco de dados.
	private Categoria categoria;


	// Relação Many-to-One com a entidade Pessoa (muitos lançamentos podem estar associados a uma pessoa, 
	// mas cada lançamento tem apenas uma pessoa)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;


	// Getters e Setters para os campos da classe Lancamento

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	// hashCode e equals para garantir a comparação correta dos objetos,

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

// Nesse código, a classe Lancamento é uma entidade JPA que representa a tabela "lancamento" no banco de dados.
// Ela possui vários campos, incluindo relacionamentos com as entidades Categoria e Pessoa,
// Sendo, os campos dataVencimento e dataPagamento mapeados para colunas específicas no banco de dados,
// o campo tipo é um enum armazenado como string, e o campo valor é do tipo BigDecimal para precisão em valores monetários.
// As relações com Categoria e Pessoa são para indicar que cada lançamento está associado a uma 
// categoria e a uma pessoa, respectivamente. Também implementamos os métodos hashCode() e equals().
// Nesse código o spring data JPA irá criar as tabelas e colunas no banco de dados com base nas anotações
// presentes na classe Lancamento, incluindo as chaves estrangeiras para as tabelas Categoria e Pessoa,
// e os tipos de dados correspondentes aos campos da classe.
// As anotações utilizadas nessa classe foram:
// @Entity que indica que a classe é uma entidade JPA;
// @Table para nomear a tabela no banco de dados;
// @Id para indicar que o campo é a chave primária da tabela;
// @GeneratedValue para definir a estratégia de geração automática do valor da chave primária (auto-incremento);
//@Column para nomear as colunas no banco de dados;
//@NotNull para indicar que os campos não pode ser nulos;
//@Enumerated para indicar que o campo é um enum e deve ser armazenado como string no banco de dados; 
//@ManyToOne para indicar as relações Many-to-One com as entidades Categoria e Pessoa;
//@JoinColumn para nomear as colunas de chave estrangeira no banco de dados. 
// Essas anotações permitem que o Spring Data JPA crie automaticamente a estrutura do banco de dados 
// e gerencie as operações de persistência para a entidade Lancamento.

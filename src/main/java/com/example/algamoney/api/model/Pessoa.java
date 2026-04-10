package com.example.algamoney.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Anotação para indicar que a classe é uma entidade JPA (mapeamento para tabela do banco de dados)
@Table(name = "pessoa") // Nomeiei a tabela para evitar conflito com a palavra reservada "person" do MySQL
public class Pessoa {

	@Id // Anotação para indicar que o campo é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automática do valor da chave primária (auto-incremento)
	private Long codigo;

	@NotNull // Anotação para indicar que o campo não pode ser nulo
	private String nome;

	@Embedded // Anotação para indicar que o campo é um componente embutido
	//  (mapeamento dos campos da classe Endereco como parte da tabela Pessoa)
	private Endereco endereco;

	@NotNull // Anotação para indicar que o campo não pode ser nulo
	private Boolean ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}

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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}

// Nesse código definimos a classe Pessoa como uma entidade JPA, mapeada para a tabela "pessoa" no banco de dados.
// A classe possui os campos codigo (ID), nome, endereco (componente embutido da classe Endereco)
// e ativo (indicando se a pessoa está ativa ou inativa). Também implementamos os métodos hashCode() e equals()
// para garantir a comparação correta dos objetos Pessoa com base no campo "codigo". 
// Nesse código o Spring Data JPA irá criar automaticamente a tabela "pessoa" no banco de dados com as colunas
// correspondentes aos campos da classe Pessoa, incluindo os campos do componente embutido Endereco. 
// Faz isso através do mapeamento JPA, onde:
// A anotação @Entity indica que a classe é uma entidade JPA;
// A anotação @Table(name = "pessoa") define o nome da tabela no banco de dados;
// A anotação @Id indica que o campo codigo é a chave primária da tabela;
// A anotação @GeneratedValue(strategy = GenerationType.IDENTITY) define a estratégia
// de geração automática do valor da chave primária (auto-incremento);
// A anotação @NotNull indica que os campos nome e ativo não podem ser nulos; 
// A anotação @Embedded indica que o campo endereco é um componente embutido, ou seja,
// os campos da classe Endereco serão mapeados como parte da tabela Pessoa.
// O método isInativo() é marcado com @JsonIgnore para que não seja incluído na serialização JSON da classe Pessoa,
// isso significa que o campo "inativo" não será retornado na resposta da API, mas pode ser utilizado internamente para
//  verificar se a pessoa está inativa.
// O @Transient indica que o método isInativo() não é um campo persistente, ou seja, não será mapeado para 
// uma coluna na tabela do banco de dados, ele é apenas um método auxiliar para verificar o estado da pessoa. 
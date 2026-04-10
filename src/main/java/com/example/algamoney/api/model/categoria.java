package com.example.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // Anotação para indicar que a classe é uma entidade JPA (mapeamento para tabela do banco de dados)
@Table(name = "categoria") // Nomeiei a tabela para evitar conflito com a palavra reservada "category" do MySQL
public class Categoria {

	@Id // Anotação para indicar que o campo é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automática do 
	// valor da chave primária (auto-incremento)
	private Long codigo; // Campo para armazenar o código (ID) da categoria

	@NotNull // Anotação para indicar que o campo não pode ser nulo
	@Size(min = 3, max = 20) // Anotação para validar o tamanho do campo (mínimo 3 caracteres e máximo 20 caracteres)
	private String nome; // Campo para armazenar o nome da categoria


// Getters e Setters para os campos da classe
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

// Sobrescreve os métodos hashCode() e equals() para garantir a comparação 
// correta dos objetos Categoria com base no campo "codigo"
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
		Categoria other = (Categoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

//Nesse código, a classe Categoria é uma entidade JPA que representa a tabela "categoria" no banco de dados. 
//Ela possui dois campos: "codigo" (ID da categoria) e "nome" (nome da categoria). 
// O campo "codigo" é a chave primária da tabela, gerada automaticamente pelo banco de dados (auto-incremento).
// nesse código o spring data jpa irá criar automaticamente a tabela "categoria" no banco de dados com as colunas
// correspondentes aos campos da classe Categoria através do mapeamento JPA, onde:
// A anotação @Entity indica que a classe é uma entidade JPA;
// A anotação @Table(name = "categoria") define o nome da tabela no banco de dados;
// A anotação @Id indica que o campo codigo é a chave primária da tabela;
// A anotação @GeneratedValue(strategy = GenerationType.IDENTITY) define a estratégia de geração automática
// do valor da chave primária (auto-incremento);
// A anotação @NotNull indica que o campo nome não pode ser nulo;
// A anotação @Size(min = 3, max = 20) valida o tamanho do campo nome;
// Os métodos hashCode() e equals() são sobrescritos para garantir a comparação correta dos objetos Categoria.



// Essa pasta "model" é onde ficam as classes que representam as entidades do domínio da aplicação,
// ou seja, as classes que representam as tabelas do banco de dados e seus relacionamentos. 
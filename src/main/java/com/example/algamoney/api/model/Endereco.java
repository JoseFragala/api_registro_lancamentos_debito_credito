package com.example.algamoney.api.model;

import javax.persistence.Embeddable;

@Embeddable // Anotação para indicar que a classe é um componente embutido (não é uma entidade independente,
//  mas sim parte de outra entidade)
public class Endereco {
// Campos do endereço, que serão mapeados como parte da tabela Pessoa
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	

// Getters e Setters para os campos da classe Endereco
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}

// Nesse código, a classe Endereco é um componente embutido (annotated with @Embeddable) 
// que representa os campos de endereço de uma pessoa. Ela não é uma entidade independente,
// mas sim parte da entidade Pessoa, e seus campos serão mapeados como parte da tabela Pessoa no banco de dados.
// ou seja, os campos da classe Endereco serão armazenados na mesma tabela que a classe Pessoa,
// e não em uma tabela separada. 
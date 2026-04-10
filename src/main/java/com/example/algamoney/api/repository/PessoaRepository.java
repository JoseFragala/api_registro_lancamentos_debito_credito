package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> { 
    // <Pessoa, Long> indica que este repositório é para a entidade Pessoa e que o tipo de ID é código.
    

}

// A interface PessoaRepository é um repositório Spring Data JPA que estende JpaRepository,
// permitindo que a aplicação realize operações de CRUD (Create, Read, Update, Delete) 
// e outras operações de acesso a dados para a entidade Pessoa, que é representada pela classe Pessoa.
// Essa interface se relaciona com a classe Pessoa, que é a entidade JPA mapeada para a tabela "pessoa" no banco de dados.
// Também se comunica com a classe PessoaResource, que é o controlador REST responsável por lidar
// com as requisições HTTP relacionadas às pessoas, usando os métodos do repositório para acessar os dados.
// O PessoaRepository fornece ao PessoaResource os métodos necessários para acessar e manipular os dados, como:
// - findAll(): para listar todas as pessoas.
// - save(): para salvar uma nova pessoa no banco de dados.
// - findById(): para buscar uma pessoa pelo seu código (ID). por ser uma versão antiga, está usando findOne()
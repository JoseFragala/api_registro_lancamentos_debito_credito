package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

// Nesse código, a interface CategoriaRepository estende JpaRepository, que é uma interface do Spring Data JPA.
// Isso significa que CategoriaRepository herda métodos para realizar operações de CRUD (Create, Read, Update, Delete)
// e outras operações de acesso a dados para a entidade Categoria, que é representada pela classe Categoria.
// Essa irá permitir que a aplicação interaja com o banco de dados para gerenciar as categorias, como listar todas as categoras.
// Essa classe se relaciona com a classe Categoria, que é a entidade JPA mapeada para a tabela "categoria" no banco.
// Também se comunica com a classe CategoriaResource, que é o controlador REST responsável por lidar
// com as requisições HTTP relacionadas às categorias, usando os métodos do repositório para acessar os dados. 
// O CategoriaRepository fornece ao CategoriaResource os métodos necessários para acessar e manipular os dados, como:
// - findAll(): para listar todas as categorias.
// - save(): para salvar uma nova categoria no banco de dados. 
// - findById(): para buscar uma categoria pelo seu código (ID). por ser uma versão antiga, está usando findOne().



// nessa pasta "repository" estão as interfaces de repositório do Spring Data JPA para as entidades da aplicação,
// como CategoriaRepository, PessoaRepository e LancamentoRepository. Essas interfaces estendem JpaRepository,
// o que permite que a aplicação realize operações de CRUD (Create, Read, Update, Delete)
// e outras operações de acesso a dados para as entidades correspondentes, como Categoria, Pessoa e Lancamento. 

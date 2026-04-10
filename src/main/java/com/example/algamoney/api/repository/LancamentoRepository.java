package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}

// Neste código, a interface LancamentoRepository estende JpaRepository, 
// que é uma interface do Spring Data JPA que fornece métodos para realizar 
// operações de CRUD (Create, Read, Update, Delete) e outras operações comuns em entidades JPA.
// Além disso, a interface LancamentoRepository também estende LancamentoRepositoryQuery,
// que é uma interface personalizada que pode conter métodos de consulta específicos para a entidade Lancamento.
// Isso permite que a aplicação tenha acesso a métodos de consulta personalizados, além dos métodos padrão 
// fornecidos pelo JpaRepository, para realizar operações específicas relacionadas à entidade Lancamento.
// A interface LancamentoRepository é usada para interagir com o banco de dados para a entidade Lancamento,
// permitindo que a aplicação realize operações como salver, buscar, atualizar e deletar lançamentos,
// bem como consultas personalizadas definidas em LancamentoRepositoryQuery.
// Fornecendo métodos como: findAll(), save(), findById(), deleteById(), etc..


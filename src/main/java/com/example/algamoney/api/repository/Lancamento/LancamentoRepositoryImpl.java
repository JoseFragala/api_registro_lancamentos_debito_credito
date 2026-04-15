package com.example.algamoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.Categoria_;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Lancamento_;
import com.example.algamoney.api.model.Pessoa_;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
// Esta classe é uma implementação personalizada do repositório de lançamentos, implementa interface LancamentoRepositoryQuery.
	
	@PersistenceContext // Anotação para injetar o EntityManager, que é usado para interagir com o banco de dados.
	private EntityManager manager; // O EntityManager é usado para criar consultas usando a API Criteria 
	// do JPA, permitindo que a aplicação construa consultas de forma programática e flexível para a entidade.
	
	@Override // Anotação para indicar que este método é uma implementação de um método definido
	// na interface LancamentoRepositoryQuery.
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable){
		// O método filtrar() é responsável por filtrar os lançamentos com base nos critérios definidos 
		// no objeto LancamentoFilter e retornar os resultados paginado usando o objeto Pageable.
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		// O CriteriaBuilder é usado para construir consultas de forma programática usando a API Criteria do JPA.
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		// O CriteriaQuery é usado para definir a consulta que será executada, especificando o tipo de resultado (Lancamento).
		Root<Lancamento> root = criteria.from(Lancamento.class);
		// O Root é usado para definir a entidade raiz da consulta, que é a entidade Lancamento neste caso.
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		//O predicate é usado para definir as restrições (filtros) da consulta com base nos critérios fornecidos no objeto LancamentoFilter.

		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		// O TypedQuery é usado para executar a consulta e obter os resultados.
		// O método adicionarRestricoesDePaginacao() é chamado para adicionar as restrições de paginação à consulta,
		// como o número da página e o tamanho da página, para garantir que os resultados sejam retornados de forma paginada.
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
		// O método getResultList() é chamado para obter a lista de resultados da consulta.
		// O resultado é retornado como um objeto PageImpl, o que permite que a aplicação trabalhe com os resultados 
		// de forma paginada, fornecendo informações sobre a página atual e o total de registros que correspondem aos critérios de filtragem.

	}

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		criteria.select(builder.construct(ResumoLancamento.class
			, root.get(Lancamento_.codigo), root.get(Lancamento_.descricao)
			, root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento)
			,root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
			, root.get(Lancamento_.categoria).get(Categoria_.nome)
			,root.get(Lancamento_.pessoa).get(Pessoa_.nome)));

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) { // Método para criar as restrições (filtros) da consulta com base nos critérios fornecidos no objeto LancamentoFilter.
		List<Predicate> predicates = new ArrayList<>(); // A lista de Predicate é usada para armazenar as restrições 
		// que serão aplicadas à consulta. Cada Predicate representa uma condição de filtragem, como 
		// "descrição contém um determinado valor" ou "data de vencimento é maior ou igual a uma determinada data".
		

		
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}
		// esse bloco de código verifica se a descrição do filtro de lançamento não está vazia.
		// Se a descrição não estiver vazia, ele adiciona uma restrição à lista de Predicate 
		// usando o método like() do CriteriaBuilder para criar uma condição de filtragem que verifica se a descrição do lançamento		

		
		if (lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
		}

		// esse bloco de código verifica se a data de vencimento "de" do filtro de lançamento não é nula.
		// Se a data de vencimento "de" não for nula, ele adiciona uma restrição à lista de Predicate
		// usando o método greaterThanOrEqualTo() do CriteriaBuilder para criar uma condição de filtragem
		// que verifica se a data de vencimento do lançamento é maior ou igual à data de vencimento "de" fornecida no
		// filtro de lançamento.
		
		if (lancamentoFilter.getDataVencimentoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
		}
	// esse bloco de código verifica se a data de vencimento "até" do filtro de lançamento não é nula.
	// Se for nula, ele adiciona uma restrição à lista de Predicate usando o método
	// lessThanOrEqualTo() do CriteriaBuilder para criar uma condição de filtragem que verifica se a data 
	// de vencimento do lançamento é menor ou igual à data de vencimento "até" fornecida no filtro de lançamento.	

		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	// O método criarRestricoes() retorna um array de Predicate, que é usado para aplicar 
	// as restrições de filtragem à consulta usando o método where() do CriteriaQuery.

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	// Esse bloco de código é responsável por adicionar as restrições de paginação à consulta.
	// Ele calcula o número da página atual, o total de registros por página e o índice do primeiro registro da página.
	// Em seguida, ele usa os métodos setFirstResult() e setMaxResults() do TypedQuery para definir o
	// índice do primeiro registro a ser retornado e o número máximo de registros a serem retornados. 
	
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	// Esse bloco de código é responsável por calcular o total de registros que corresponde aos critérios
	// de filtragem fornecidos no objeto LancamentoFilter. Ele cria uma consulta usando a API Criteria do jpa
	// para contar o número de registros que correspondem aos critérios de filtragem, aplicando as mesmas restrições
	// que foram aplicadas na consulta de filtragem. O resultado é retornado como um Long, representando o total de registros
	// que correspondem aos critérios de filtragem. Esse total é usado para fornecer informações sobre o número total de
	// registros disponíveis para a paginação, permitindo que a aplicação
	// saiba quantas páginas existem com base no número total de registros e no tamanho da página.	

	
}


// nesse código, a classe LancamentoRepositoryImpl é uma implementação personalizada do repositório de lançamentos,
// ou seja, significa que ela fornece uma implementação específica para os métodos de filtragem de lançamentos
// usando a API Criteria do JPA. 
// As implementações são:
// - O método filtrar() é responsável por filtrar os lançamentos com base nos critérios definidos no objeto LancamentoFilter que são:
// - descrição (verifica se a descrição do lançamento contém um determinado valor, ignorando maiúsculas e minúsculas);
// - data de vencimento "de" (verifica se a data de vencimento do lançamento é maior ou igual a uma determinada data);
// - data de vencimento "até" (verifica se a data de vencimento do lançamento é menor ou igual a uma determinada data).
// O método criarRestricoes() é usado para criar as restrições (filtros) da consulta
// com base nos critérios fornecidos no objeto LancamentoFilter, retornando um array de Predicate que é usado para 
// aplicar as restrições de filtragem à consulta usando o método where() do CriteriaQuery.
// O método adicionarRestricoesDePaginacao() é usado para adicionar as restrições de paginação à consulta,
// calculando o número da página atual, o total de registros por página e o índice do primeiro registro da página,
// e usando os métodos setFirstResult() e setMaxResults() do TypedQuery para definir o índice do primeiro
// registro a ser retornado e o número máximo de registros a serem retornados. 
// O método total() é usado para calcular o total de registros que corresponde aos 
// critérios de filtragem, criando uma consulta usando a API Criteria do JPA para contar o número de registros que
// correspondem aos critérios de filtragem, aplicando as mesmas restrições que foram aplicadas na consulta de filtragem,
// e retornando o resultado como um Long, representando o total de registros que correspondem aos critérios de filtragem.
// A classe LancamentoRepositoryImpl (Impl de implementação) se relaciona com a interface LancamentoRepositoryQuery.
// Em resumo a classe LancamentoRepositoryImpl é uma implementação personalizada do repositório de lançamentos 
// que fornece métodos para filtrar lançamentos com base em critérios específicos, 
// usando a API Criteria do JPA para construir consultas de forma programática e flexível, 
// e também fornece suporte para paginação dos resultados.
// Nesse código o Spring possibilita atraves das anotações a implementação automática dos métodos de acesso a dados,
//  mas para casos mais complexos, como filtragem personalizada, é necessário criar uma implementação personalizada.
// as anotações utilizadas:
// - @PersistenceContext: para injetar o EntityManager, que é usado para interagir com o banco de dados.
// - @Override: para indicar que um método é uma implementação de um método definido em uma interface.
// O EntityManager é usado para criar consultas usando a API criteria do JPA, permitindo que a aplicação 
// construa consultas de forma programática e flexível para a entidade Lancamento, aplicando filtros de paginação.







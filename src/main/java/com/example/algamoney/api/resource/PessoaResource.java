package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.PessoaService;

@RestController // Anotação para indicar que esta classe é um controlador REST.
@RequestMapping("/pessoas")// Anotação p/ mapear as requisições p/ o endpoint "/pessoas" para os métodos desta classe.
public class PessoaResource {

	@Autowired// Anotação para injetar a dependência do repositório de pessoas.
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}

// nesse código, a classe PessoaResource é um controlador REST que lida com as requisições HTTP relacionadas à entidade Pessoa.
// Ela se relaciona com a classe Pessoa, que é a entidade JPA.
// também se comunica com a classe PessoaRepository, que é o repositório Spring Data JPA para entidade Pessoa.
// A classe PessoaResource usa os métodos do repositório para acessar e manipular os dados das pessoas no banco de dados.
// Os métodos da classe PessoaResource são mapeados para os endpoints REST usando as anotações do Spring MVC:
// @GetMapping, @PostMapping, @PutMapping e @DeleteMapping, permitindo que a aplicação responda às requisições HTTP para criar, ler, atualizar e excluir pessoas.
// O método criar() é responsável por criar uma nova pessoa, usando o método save() do repositório para salvar a pessoa no banco de dados e publicando um evento de recurso criado (RecursoCriadoEvent) para notificar outras partes da aplicação sobre a criação de um novo recurso (pessoa).
// O método buscarPeloCodigo() é responsável por buscar uma pessoa pelo seu código (ID), usando o método findOne() do repositório para buscar a pessoa no banco de dados e retornando uma resposta HTTP adequada (200 OK se a pessoa for encontrada, 404 Not Found se não for encontrada).
// O método remover() é responsável por excluir uma pessoa pelo seu código (ID), usando o
// método delete() do repositório para excluir a pessoa do banco de dados e retornando uma resposta HTTP 204 No Content.
// O método atualizar() é responsável por atualizar uma pessoa existente, usando o serviço de pessoas para
// realizar a lógica de negócio ao atualizar a pessoa e retornando a pessoa atualizada em uma resposta HTTP 200 OK.
// O método atualizarPropriedadeAtivo() é responsável por atualizar a propriedade "ativo" de uma pessoa,
// usando o serviço de pessoas para realizar a lógica de negócio ao atualizar a propriedade e retornando uma resposta HTTP 204 No Content.
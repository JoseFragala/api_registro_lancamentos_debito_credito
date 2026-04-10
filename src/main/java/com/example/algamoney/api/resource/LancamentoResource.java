package com.example.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.service.LancamentoService;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@RestController // Anotação para indicar que esta classe é um controlador REST.
@RequestMapping("/lancamentos") // Anotação para mapear as requisições para o endpoint 
// "/lancamentos" para os métodos desta classe. Isso significa que todas as requisições para "/lancamentos"
public class LancamentoResource {

	@Autowired // Anotação para injetar a dependência do repositório de lançamentos.
	// isso significa que a classe pode usar os métodos do repositório para acessar os dados dos lançamentos.
	private LancamentoRepository lancamentoRepository; 
	// Aqui, a interface LancamentoRepository é injetada na classe LancamentoResource
	
	@Autowired // Anotação para injetar a dependência do serviço de lançamentos.
	private LancamentoService lancamentoService;
	
	@Autowired // Anotação para injetar a dependência do ApplicationEventPublisher,
	private ApplicationEventPublisher publisher;
	
	@Autowired // Anotação para injetar a dependência do MessageSource.
	private MessageSource messageSource;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class }) // Anotação para indicar que este método 
	// deve ser chamado para tratar a exceção PessoaInexistenteOuInativaException.
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
	// O método handlePessoaInexistenteOuInativaException é responsável por tratar a exceção PessoaInexistenteOuInativaException,
	// O "ResponseEntity<Object>" indica que o método retorna uma resposta HTTP genérica, que pode conter
	// qualquer tipo de objeto no corpo da resposta. O método recebe a exceção como parâmetro, 
	// permitindo que ele acesse as informações da exceção para criar uma resposta adequada. 
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
	// O método getMessage() do MessageSource é usado para obter a mensagem de erro localizada para o usuário.
		String mensagemDesenvolvedor = ex.toString(); 
		// A variável mensagemDesenvolvedor é definida como a representação em string da exceção.
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		// A variável erros é criada como uma lista contendo um único objeto Erro.
		// O objeto Erro é criado usando a mensagem para o usuário e a mensagem para o desenvolvedor.
		return ResponseEntity.badRequest().body(erros); 
		// retorna uma ResponseEntity com o status HTTP 400 Bad Request e o corpo da resposta contendo a lista de erros.
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepository.delete(codigo);
	}
	
}

// Nesse código, a classe LancamentoResource é um controlador REST responsável por lidar com as requisições HTTP
// relacionadas à entidade Lancamento. Ela possui métodos para listar, buscar, criar e remover Lancamentos.
// A classe usa o repositório de lançamentos para acessar os dados no banco de dados, o serviço de lançamentos
// Usar a classe LancamentoService para realizar a lógica de negócio ao criar um novo lançamento,
// e o ApplicationEventPublisher para publicar eventos na aplicação, como o evento de recurso criado (RecursoCriadoEvent).
// A classe também possui um método para tratar a exceção PessoaInexistenteOuInativaException,
// retornando uma resposta HTTP adequada com mensagens de erro para o usuário e para o desenvolvedor.
// A classe LancamentoResource se relaciona com a classe Lancamento, que é a entidade JPA. 
// Ela também se relaciona com a interface LancamentoRepository, que é o repositório Spring Data JPA 
// para a entidade Lancamento, e com a classe LancamentoService, que é o serviço responsável pela lógica de negócio.
// A classe também se relaciona com a classe RecursoCriadoEvent, que é um evento personalizado
// usado para notificar outras partes da aplicação sobre a criação de um novo recurso (lançamento)
// Nesse código o Spring Boot irá criar os endpoints REST para a entidade Lancamento, permitindo que a
// aplicação interaja com os lancamentos.
// as anotações são:
// @RestController para indicar que a classe é um controlador REST;
// @RequestMapping para mapear as requisições para o endpoint "/lancamentos" para os métodos desta classe;
// @Autowired para injetar as dependências do repositório, serviço, ApplicationEventPublisher e MessageSource.
// @GetMapping, @PostMapping, @DeleteMapping para mapear as requisições HTTP GET, POST e DELETE para os métodos correspondentes;
// @Valid para validar os dados de entrada usando as anotações de validação definidas na classe Lancamento.
// @RequestBody para indicar que o objeto Lancamento deve ser desserializado a partir do JSON da requisição;
// @PathVariable para indicar que o parâmetro código deve ser extraído da URL da requisição.
// @ExceptionHandler para indicar que o método deve ser chamado para tratar a exceção PessoaInexistenteOuInativaException.
// @ResponseStatus para indicar o status HTTP a ser retornado quando um lançamento for removido com sucesso (204 no Content).


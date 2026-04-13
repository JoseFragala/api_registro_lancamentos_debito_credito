package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController // Anotação para indicar que esta classe é um controlador REST,
// responsável por lidar com as requisições HTTP relacionadas à entidade Categoria.
@RequestMapping("/categorias") // Anotação para mapear as requisições para o endpoint "/categorias" 
// para os métodos desta classe. Isso significa que todas as requisições para "/categorias" 
// serão tratadas por esta classe.
public class CategoriaResource {

	@Autowired // Anotação para injetar a dependência do repositório de categorias,
	// permitindo que a classe use os métodos do repositório para acessar os dados das categorias no banco de dados. 
	private CategoriaRepository categoriaRepository;
	
	@Autowired // Anotação para injetar a dependência do ApplicationEventPublisher,
	// permitindo que a classe publique eventos na aplicação, como o evento de recurso criado (RecursoCriadoEvent)
	// para notificar outras partes da aplicação sobre a criação de um novo recurso (categoria) 
	// e permitir que elas reajam a esse evento, como adicionar o cabeçalho "Location" na resposta HTTP com a 
	// URL do recurso recém-criado.
	private ApplicationEventPublisher publisher;
	

	@GetMapping // Anotação para mapear as requisições GET para o endpoint "/categorias" para este método,
	// permitindo que ele seja chamado quando uma requisição GET for feita para "/categorias".
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<Categoria> listar() { // Método para listar todas as categorias.
	//  Ele retorna uma lista de objetos Categoria, que são obtidos do banco de dados usando
	//  o método findAll() do repositório de categorias.	
		return categoriaRepository.findAll();
	}
	
	@PostMapping // Anotação para mapear as requisições POST para o endpoint "/categorias" para este método,
	// permitindo que ele seja chamado quando uma requisição POST for feita para "/categorias".
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA')and #oauth2.hasScope('write')")
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) { 
		// Método para criar uma nova categoria. Ele recebe um objeto Categoria no corpo da requisição 
		// (anotado com @RequestBody para indicar que deve ser desserializado a partir do JSON da requisição)
		// e validado (anotado com @Valid para aplicar as validações definidas na classe Categoria, como 
		// @NotNull e @Size). Ele também recebe o objeto HttpServletResponse para permitir a manipulação da resposta HTTP.
		Categoria categoriaSalva = categoriaRepository.save(categoria); 
		// O método save() do repositório de categorias é usado para salvar a nova categoria no banco de dados.
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		// O método publishEvent() do ApplicationEventPublisher é usado para publicar um evento de recurso criado
		// (RecursoCriadoEvent) com as informações da categoria recém-criada, permitindo que outras partes da aplicação
		// reajam a esse evento, como adicionar o cabeçalho "Location" na resposta HTTP com a URL do recurso recém-criado.
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		// O método retorna uma ResponseEntity com o status HTTP 201 Created e o corpo da resposta contendo 
		// a categoria recém-criada (categoriaSalva).
	}
	
	@GetMapping("/{codigo}") // Anotação para mapear as requisições GET para o endpoint 
	// "/categorias/{codigo}" para este método, permitindo que ele seja chamado quando
	// uma requisição GET for feita para "/categorias/{codigo}", onde {codigo} é um parâmetro de caminho.
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')and #oauth2.hasScope('read')")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) { 
		//Método para buscar uma categoria pelo seu código (ID).
		// Ele recebe o código da categoria como um parâmetro de caminho
		// (anotado com @PathVariable para indicar que deve ser extraído da URL da requisição).
		 Categoria categoria = categoriaRepository.findOne(codigo); 
		 // O método findOne() do repositório de categorias é usado para buscar a categoria no 
		 // banco de dados com base no código fornecido.
		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
		 // O método retorna uma ResponseEntity com o status HTTP 200 OK e 
		 // o corpo da resposta contendo a categoria encontrada, ou 
		 // uma ResponseEntity com o status HTTP 404 Not Found se a categoria não for encontrada (categoria for null).
	}
	
}

// Nesse código, a classe CategoriaResource é um controlador REST que lida com as requisições
// HTTP relacionadas à entidade Categoria. Ela possui métodos para listar todas as categorias,
// criar uma nova categoria e buscar uma categoria pelo seu código (ID). 
// A classe usa o repositório de categorias para acessar os dados no banco de dados e o 
// ApplicationEventPublisher para publicar eventos na aplicação, como o evento de recurso criado (RecursoCriadoEvent)
// evento é um mecanismo de comunicação entre componentes da aplicação, permitindo que eles se comuniquem 
// de forma desacoplada, ou seja, sem depender diretamente uns dos outros.
// nesse caso, o evento de recurso criado é publicado quando uma nova categoria é criada, 
// permitindo que outras partes da aplicação reajam a esse evento, como adicionar o cabeçalho 
// "Location" na resposta HTTP com a URL do recurso recém-criado. 
// Nesse código o Spring Boot irá criar automaticamente os endpoints REST para as operações de
// listagem, criação e busca de categorias, com base nas anotações presentes que são:
// @RestController para indicar que a classe é um controlador REST;
// @RequestMapping("/categorias") para mapear as requisições para o endpoint
// "/categorias" para os métodos desta classe;
// @Autowired para injetar as dependências do repositório de categorias e do ApplicationEventPublisher;
// @GetMapping para mapear as requisições GET para os métodos de listagem e busca;
// @PostMapping para mapear as requisições POST para o método de criação;
// @Valid para validar os dados recebidos no corpo da requisição;
// @RequestBody para indicar que o objeto Categoria deve ser desserializado a partir do JSON da requisição;
// @PathVariable para indicar que o código da categoria deve ser extraído da URL da requisição. 

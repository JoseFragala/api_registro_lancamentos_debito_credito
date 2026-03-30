package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController // controlador REST, ou seja, é responsável por receber
// as requisições HTTP e retornar as respostas HTTP
@RequestMapping("/categorias") // mapeamento da requisição HTTP, ou seja, 
// quando o cliente fizer uma requisição para /categorias, essa classe será responsável 
// por atender essa requisição
public class CategoriaResource {


    @Autowired // ache uma implementação de CategoriaRepository e injete aqui.
    private CategoriaRepository categoriaRepository;

    @GetMapping // sabe que é esse método que deve ser chamado quando o cliente fizer uma 
    // requisição GET para /categorias
    public List<Categoria> listar() {
         return categoriaRepository.findAll(); // faz um SELECT * FROM categoria, ou seja,
        //  retorna todas as categorias cadastradas no banco de dados
            }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // quando o cliente fizer uma requisição POST para /categorias,
    // esse método será chamado e o status da resposta HTTP será 201 Created.
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria); // faz um INSERT INTO categoria 
        // (nome) VALUES (categoria.getNome())

       URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}") 
        .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());
    // o método criar() recebe um objeto Categoria no corpo da requisição HTTP, 
    // salva esse objeto no banco de dados e retorna o status 201 Created. 
    // Além disso, o método também retorna o URI da nova categoria criada no 
    // header Location da resposta HTTP.
        return ResponseEntity.created(uri).body(categoriaSalva);
    }        

    @GetMapping("/{codigo}")
    public Categoria buscarPeloCodigo(@PathVariable Long codigo){
        return categoriaRepository.findById(codigo).orElse(null);
    }// o método buscarPeloCodigo() recebe um código de categoria na URL,
    // busca essa categoria no banco de dados e retorna o objeto Categoria correspondente.

    


    
}

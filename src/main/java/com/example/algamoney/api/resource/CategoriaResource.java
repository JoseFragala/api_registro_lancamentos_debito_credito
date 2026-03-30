package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.algamoney.api.model.categoria;
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
    public List<categoria> listar() {
         return categoriaRepository.findAll(); // faz um SELECT * FROM categoria, ou seja,
        //  retorna todas as categorias cadastradas no banco de dados
            }
    


    
}

package com.example.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity // é uma entidade do banco de dados
@Table(name = "categoria") // nome da tabela no banco de dados
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// gera o código automaticamente, 
    // incrementando a cada nova categoria criada
    private Long codigo;

    @NotNull // o nome da categoria não pode  ser nulo
    @Size(min = 3, max = 20) // o nome da categoria deve ter entre 3 e 20 caracteres
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Long getCodigo() {
        return codigo;
    }
  
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

 


    
}

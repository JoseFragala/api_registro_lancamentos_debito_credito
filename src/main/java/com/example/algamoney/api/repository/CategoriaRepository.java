package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.algamoney.api.model.categoria;

public interface CategoriaRepository extends JpaRepository<categoria, Long> {
    
}

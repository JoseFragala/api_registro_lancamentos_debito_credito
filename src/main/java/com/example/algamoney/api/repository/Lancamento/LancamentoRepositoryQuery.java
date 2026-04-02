package com.example.algamoney.api.repository.Lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.Filter.LancamentoFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface LancamentoRepositoryQuery  {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

    
}
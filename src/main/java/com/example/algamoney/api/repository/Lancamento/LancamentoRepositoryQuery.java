package com.example.algamoney.api.repository.Lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.Filter.LancamentoFilter;
import java.util.List;

public interface LancamentoRepositoryQuery  {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

    
}
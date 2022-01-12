package com.flying.controller;

import com.flying.model.Despesa;
import com.flying.repository.DespesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/expense"})
public class DespesaController {
    
    @Autowired
    private DespesaRepository repository;

    public DespesaController(DespesaRepository despesaRepository) {
        this.repository = despesaRepository;
    }

    public DespesaController() {}

    /**  Cadastrar uma nova despesa */
    @PostMapping("/create")
    public void create(@RequestBody Despesa despesa) {
        repository.save(despesa);
    }
    
    /** Editar despesa */ 
    @PutMapping("/edit/{id}")
    public ResponseEntity<Despesa> update(@RequestBody Despesa novaDespesa, @PathVariable(value="id") Long id) {
        return repository.findById(id).map(despesa -> {
            despesa.setDataPagamento(novaDespesa.getDataPagamento());
            despesa.setDataPagamentoEsperado(novaDespesa.getDataPagamentoEsperado());
            despesa.setTipoDespesa(novaDespesa.getTipoDespesa());
            despesa.setValor(novaDespesa.getValor());
            
            Despesa despesaAtualizada = repository.save(despesa);
            return ResponseEntity.ok().body(despesaAtualizada);

        }).orElse(ResponseEntity.notFound().build());
    }

    /** Remover despesa */ 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
        return repository.findById(id).map(despesa -> {
            repository.deleteById(id);

            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    /** Listar todas as despesas */ 
    @GetMapping("/all")
    public Iterable<Despesa> getAll() {
        return repository.findAll();
    }
}

package com.flying.controller;

import com.flying.model.Receita;
import com.flying.repository.ReceitaRepository;

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
@RequestMapping({"/revenue"})
public class ReceitaController {
    
    @Autowired
    private ReceitaRepository repository;

    public ReceitaController(ReceitaRepository receitaRepository) {
        this.repository = receitaRepository;
    }

    public ReceitaController() {}

     /**  Cadastrar uma nova receita */
     @PostMapping("/create")
     public void create(@RequestBody Receita receita) {
         repository.save(receita);
     }
     
     /** Editar receita */ 
     @PutMapping("/edit/{id}")
     public ResponseEntity<Receita> update(@RequestBody Receita novaReceita, @PathVariable(value="id") Long id) {
         return repository.findById(id).map(receita -> {
             receita.setDataRecebimento(novaReceita.getDataRecebimento());
             receita.setDataRecebimentoEsperado(novaReceita.getDataRecebimentoEsperado());
             receita.setDescricao(novaReceita.getDescricao());
             receita.setTipoReceita(novaReceita.getTipoReceita());
             receita.setValor(novaReceita.getValor());
             
             Receita receitaAtualizada = repository.save(receita);
             return ResponseEntity.ok().body(receitaAtualizada);
 
         }).orElse(ResponseEntity.notFound().build());
     }
 
     /** Remover receita */ 
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
         return repository.findById(id).map(receita -> {
             repository.deleteById(id);
 
             return ResponseEntity.ok().build();
         }).orElse(ResponseEntity.notFound().build());
     }
 
     /** Listar todas as receitas */ 
     @GetMapping("/all")
     public Iterable<Receita> getAll() {
         return repository.findAll();
     }
}
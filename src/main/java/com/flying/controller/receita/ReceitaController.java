package com.flying.controller.receita;

import java.util.List;

import com.flying.model.Receita;
import com.flying.repository.ReceitaRepository;

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
@RequestMapping({"/revenues"})
public class ReceitaController {

    private ReceitaRepository repository;

    public ReceitaController(ReceitaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody Receita receita) {
        repository.save(receita);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<Receita> updateRevenue(@RequestBody Receita novaReceita, @PathVariable(value="id") Long id) {
        return repository.findById(id).map(receita -> {
            receita.setConta(novaReceita.getConta());
            receita.setDataRecebimento(novaReceita.getDataRecebimento());
            receita.setDataRecebimentoEsperado(novaReceita.getDataRecebimentoEsperado());
            receita.setDescricao(novaReceita.getDescricao());
            receita.setTipoReceita(novaReceita.getTipoReceita());
            receita.setValor(novaReceita.getValor());
            
            Receita receitaAtualizada = repository.save(receita);
            return  ResponseEntity.ok().body(receitaAtualizada);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
        return repository.findById(id).map(receita -> {
            repository.deleteById(id);

            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Receita> getAllRevenues() {
        return repository.findAll();
    }

    @GetMapping("/total-expenses/{id}")
    public double getTotalRevenues(@PathVariable(value="id") String id) {
        double totalRevenues = 0;

        for (Receita receita : repository.findAll()) {
            totalRevenues += receita.getValor();
        }

        return totalRevenues;
    }
}

package com.flying.controller.despesa;

import java.util.List;

import com.flying.model.Despesa;
import com.flying.repository.DespesaRepository;

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
@RequestMapping({"/expenses"})
public class DespesaController {

    private DespesaRepository repository;

    public DespesaController(DespesaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody Despesa despesa) {
        repository.save(despesa);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<Despesa> update(@RequestBody Despesa novaDespesa, @PathVariable(value="id") Long id) {
        return repository.findById(id).map(despesa -> {
            despesa.setConta(novaDespesa.getConta());
            despesa.setValor(novaDespesa.getValor());
            despesa.setDataPagamento(novaDespesa.getDataPagamento());
            despesa.setDataPagamentoEsperado(novaDespesa.getDataPagamentoEsperado());
            despesa.setTipoDespesa(novaDespesa.getTipoDespesa());
            
            Despesa despesaAtualizada = repository.save(despesa);
            return  ResponseEntity.ok().body(despesaAtualizada);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
        return repository.findById(id).map(despesa -> {
            repository.deleteById(id);

            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Despesa> getAllExpenses() {
        return repository.findAll();
    }

    @GetMapping("/total-expenses/{id}")
    public double getTotalExpenses(@PathVariable(value="id") String id) {
        double totalExpenses = 0;

        for (Despesa despesa : repository.findAll()) {
            totalExpenses += despesa.getValor();
        }

        return totalExpenses;
    }
}

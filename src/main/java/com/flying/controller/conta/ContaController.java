package com.flying.controller.conta;

import java.util.List;

import com.flying.model.Conta;
import com.flying.repository.ContaRepository;

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
@RequestMapping({"/accounts"})
public class ContaController {

    private ContaRepository repository;

    public ContaController(ContaRepository contaRepository) {
        this.repository = contaRepository;
    }

    @PostMapping("/create")
    public void create(@RequestBody Conta conta) {
        repository.save(conta);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<Conta> update(@RequestBody Conta novaConta, @PathVariable(value="id") Long id) {
        return repository.findById(id).map(conta -> {
            conta.setSaldo(novaConta.getSaldo());
            conta.setTipoConta(novaConta.getTipoConta());
            conta.setInstituicaoFinanceira(novaConta.getInstituicaoFinanceira());
            
            Conta contaAtualizada = repository.save(conta);
            return  ResponseEntity.ok().body(contaAtualizada);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
        return repository.findById(id).map(conta -> {
            repository.deleteById(id);

            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Conta> getAllAccounts() {
        return repository.findAll();
    }

    @PostMapping("/transaction/{idContaEmissor}/{idContaReceptor}")
    public void makeTransaction(@PathVariable(value="idContaEmissor") String idContaEmissor,
     @PathVariable(value="idContaReceptor") String idContaReceptor,
     double quantidadeTransferir) {
        // TODO: use database to make transactions between accounts and add validations.
    }

    @GetMapping("/total-balance/{id}")
    public void getAccountBalance(@PathVariable(value="id") String id) {
        // TODO: use database to get total balance
    }
}
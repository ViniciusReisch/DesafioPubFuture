package com.flying.controller;

import com.flying.model.Conta;
import com.flying.repository.ContaRepository;
import com.flying.validation.ContaValidation;

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
@RequestMapping({"/accounts"})
public class ContaController {

    @Autowired
    private ContaRepository repository; 

    public ContaController(ContaRepository contaRepository) {
        this.repository = contaRepository;
    }

    public ContaController() {}

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
    public Iterable<Conta> getAllAccounts() {
        return repository.findAll();
    }

    @PostMapping("/transaction/{idContaEmissor}/{idContaReceptor}")
    public void makeTransaction(@PathVariable(value="idContaEmissor") Long idContaEmissor,
     @PathVariable(value="idContaReceptor") Long idContaReceptor,
     @RequestBody double quantidadeTransferir) {
         // Pegando saldo atráves do ID da conta emissora.
        double saldo = repository.getById(idContaEmissor).getSaldo();
        System.out.print(String.valueOf(saldo));

        if (ContaValidation.isPossibleTransaction(quantidadeTransferir, saldo)) {
            System.out.print("É possível fazer transferência.");
        } else {
            System.out.print("Não é possível fazer transferência.");
        }
    }

    @GetMapping("/total-balance/{id}")
    public void getAccountBalance(@PathVariable(value="id") String id) {
        // TODO: use database to get total balance
    }
}
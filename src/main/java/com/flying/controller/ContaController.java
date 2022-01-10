package com.flying.controller;

import java.util.Optional;

import com.flying.model.Conta;
import com.flying.repository.ContaRepository;
import com.flying.validation.ContaValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class Saldo {
    double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

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
     @RequestBody Saldo saldo) {
        // Obs.: 'Saldo' está sendo usado como parâmetro para a transação pois usar apenas um double resulta, por algum motivo, um erro de deserialize.
        // com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize value of type `java.lang.Double` from Object value
        double valorASerTransferido = saldo.getSaldo();
        try {
            Conta contaEmissora = repository.getById(idContaEmissor);
            Conta contaReceptora = repository.getById(idContaReceptor);

            // Caso o valor a ser transferido seja menor ou igual à quantidade de saldo
            if (ContaValidation.isPossibleTransaction(valorASerTransferido, contaEmissora.getSaldo())) {
                // ... o valor é adicionado à conta receptora
                contaReceptora.setSaldo(contaReceptora.getSaldo() + valorASerTransferido);
                update(contaReceptora, idContaReceptor);
                // E descontado da conta emissora.
                contaEmissora.setSaldo(contaEmissora.getSaldo() - valorASerTransferido);
                update(contaEmissora, idContaEmissor);
            } else {
                System.out.println("Não é válido");
            }
        } catch (Exception e) {
            System.out.print("Conta com identificador " + idContaReceptor + " não foi encontrado.");
        }

    }

    @GetMapping("/total-balance/{id}")
    public void getAccountBalance(@PathVariable(value="id") Long id) {
        // TODO: use database to get total balance
    }
}
package com.flying.controller.conta;

import java.util.ArrayList;
import java.util.List;

import com.flying.model.conta.Conta;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    public List<Conta> accounts = new ArrayList<Conta>();

    @PostMapping("/account/create")
    public void createAccount(@RequestBody Conta conta) {
        accounts.add(conta); 
    }
    
    @PutMapping("/account/edit/{id}")
    public void updateAccount(@RequestBody Conta conta) {
        // TODO: use database to update accounts with id.
    }

    @DeleteMapping("/account/delete/{id}")
    public void deleteAccount() {
        // TODO: use database to delete accounts with id.
    }

    @GetMapping("/accounts/all")
    public List<Conta> getAllAccounts() {
        return accounts;
    }

    @PostMapping
    public void makeTransaction(@RequestBody String idContaEmissor, String idContaReceptor, double quantidadeTransferir) {
        // TODO: use database to make transactions between accounts and add validations.
    }

    @GetMapping("/account/total-balance/{id}")
    public void getAccountBalance() {
        // TODO: use database to get total balance
    }
}
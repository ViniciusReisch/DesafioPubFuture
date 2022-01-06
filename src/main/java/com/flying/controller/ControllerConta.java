package com.flying.controller;

import java.util.ArrayList;
import java.util.List;

import com.flying.model.conta.Conta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerConta {

    public List<Conta> accounts = new ArrayList<Conta>();

    @PostMapping("/accounts/create")
    public void createNewAccount(@RequestBody Conta conta) {
        accounts.add(conta);
    }

    @GetMapping("/accounts/all")
    public List<Conta> getAllAccounts() {
        return accounts;
    }
}

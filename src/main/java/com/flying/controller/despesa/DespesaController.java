package com.flying.controller.despesa;

import java.util.ArrayList;
import java.util.List;

import com.flying.model.despesa.Despesa;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DespesaController {

    public List<Despesa> expenses = new ArrayList<Despesa>();

    @PostMapping("/expense/create")
    public void createExpense(@RequestBody Despesa despesa) {
        expenses.add(despesa);
    }
    
    @PutMapping("/expense/edit/{id}")
    public void updateExpense(@RequestBody Despesa despesa) {
        // TODO: use database to update expenses with id.
    }

    @DeleteMapping("/expense/delete/{id}")
    public void deleteExpense() {
        // TODO: use database to delete expenses with id.
    }

    @GetMapping("/expenses/all")
    public List<Despesa> getAllExpenses() {
        // TODO: add filter
        return expenses;
    }

    @GetMapping("/expense/total-expenses/{id}")
    public double getTotalExpenses() {
        double totalExpenses = 0;

        for (Despesa despesa : expenses) {
            totalExpenses += despesa.getValor();
        }

        return totalExpenses;
    }
}

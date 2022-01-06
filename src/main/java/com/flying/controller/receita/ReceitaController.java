package com.flying.controller.receita;

import java.util.ArrayList;
import java.util.List;

import com.flying.model.Receita;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceitaController {
    public List<Receita> revenues = new ArrayList<Receita>();

    @PostMapping("/revenue/create")
    public void createRevenue(@RequestBody Receita receita) {
        revenues.add(receita);
    }
    
    @PutMapping("/revenue/edit/{id}")
    public void updateRevenue(@RequestBody Receita receita, @PathVariable(value="id") String id) {
        // TODO: use database to update expenses with id.
    }

    @DeleteMapping("/revenue/delete/{id}")
    public void deleteRevenue(@PathVariable(value="id") String id) {
        // TODO: use database to delete expenses with id.
    }

    @GetMapping("/revenues/all")
    public List<Receita> getAllRevenues() {
        // TODO: add filter
        return revenues;
    }

    @GetMapping("/revenue/total-expenses/{id}")
    public double getTotalRevenues(@PathVariable(value="id") String id) {
        double totalRevenues = 0;

        for (Receita receita : revenues) {
            totalRevenues += receita.getValor();
        }

        return totalRevenues;
    }
}

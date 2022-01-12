package com.flying.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "receitas")
public class Receita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    private double valor;

    // TODO: Change to DateTime
    @Column(name = "data_recebimento", nullable = false)
    private String dataRecebimento;

    @Column(name = "data_recebimento_esperado", nullable = false)
    private String dataRecebimentoEsperado;

    @Column(name = "descricao", nullable = false)  
    private String descricao;

    @Column(name = "tipo_receita", nullable = false)
    private String tipoReceita;

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }
    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getDataRecebimentoEsperado() {
        return dataRecebimentoEsperado;
    }
    public void setDataRecebimentoEsperado(String dataRecebimentoEsperado) {
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoReceita() {
        return tipoReceita;
    }
    public void setTipoReceita(String tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
}

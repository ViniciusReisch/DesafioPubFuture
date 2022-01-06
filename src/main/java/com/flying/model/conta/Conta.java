package com.flying.model.conta;

public class Conta {

    private double saldo;
    private String tipoConta;
    private String instiuicaoFinanceira;

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getInstiuicaoFinanceira() {
        return instiuicaoFinanceira;
    }
    public void setInstiuicaoFinanceira(String instiuicaoFinanceira) {
        this.instiuicaoFinanceira = instiuicaoFinanceira;
    }
}
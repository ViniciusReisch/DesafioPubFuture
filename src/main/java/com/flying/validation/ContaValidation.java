package com.flying.validation;

public class ContaValidation {
    
    public static boolean isPossibleTransaction(double valorASerTransferido, double valorAtualDaConta) {
        if(valorASerTransferido > valorAtualDaConta) return false;
        return true;
    }
}

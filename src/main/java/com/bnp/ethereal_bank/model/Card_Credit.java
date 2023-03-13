package com.bnp.ethereal_bank.model;

import java.util.Calendar;

import jakarta.persistence.Entity;

//@Entity
public class Card_Credit extends Card {
    int plafond_mensal; // definido na criação do cartão

    public Card_Credit(int val_dia, double saldo) {
        super(val_dia, saldo);
    }

    public boolean isPlafondExceeded(double valor) {

        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1; 
    
        double totalSpendingThisMonth = getValorTotalGastoMes(currentMonth);
        if (totalSpendingThisMonth + valor > plafond_mensal) {
            return true;
        }
        return false;
    }
    
    private double getValorTotalGastoMes(int month) {
        return Math.random() * 5000;
    }



    /*
     * @Override - dar a opção de fazer cash advance ou levantar da Account
     * void levantamento(double valor) {
     * //
     * 
     * }
     */
    
}

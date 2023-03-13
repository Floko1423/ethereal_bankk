package com.bnp.ethereal_bank.model;
import jakarta.persistence.Entity;

//@Entity
public class Card_Debit extends Card {

    public Card_Debit( int val_dia, double saldo) {
        super(val_dia, saldo);
    }


    
}

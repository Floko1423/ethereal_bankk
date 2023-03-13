package com.bnp.ethereal_bank.model;

import java.util.LinkedList;
import java.util.Random;

import com.bnp.ethereal_bank.model.Exceptions.DepositoFalhadoException;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cards")
public abstract class Card {

    // Client Client; // parametro desnecessario? visto que Clients na mesma Account
    // podem ter acesso aos mesmos cards se souberem o pin
    // Account Account;
    //private Long id_Account;

    @Column(name="id")
    @Id
    private String id;

    @Column(name="pin")
    private String pin;

    @Column(name="daily_value")
    private int val_dia;

    @Column(name="balance")
    private double saldo;

    public Card(int val_dia, double saldo) {
        this.id = auto_id();
        this.pin = auto_pin();
        this.val_dia = val_dia;
        this.saldo = saldo;

    }
    
    
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cartão com id: " + this.id + ", possui o pin " + this.pin + ", tem de saldo:" + this.saldo;
    }

    public String auto_pin() { // fazer com que este método invoque outro que permita ao Client mudar na 1ª
                               // utilizacao

        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        this.pin = String.format("%04d", number);
        return pin;
    }

    public String auto_id() {

        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        this.id = String.format("%04d", number);
        return id;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

   

    public void transacao(double valor) throws Exception { // fazer method overload em Account para conseguir transferir
                                                           // para outras Accounts

        if (valor > 200) {
            throw new Exception("Erro: transacao excede o limite de 200 euros.");
        } else if (valor > saldo) {
            throw new Exception("Erro: transacao não pode exceder o saldo da Account"); // este saldo tem de ser o saldo
                                                                                      // total da Account
        } else {
            saldo = saldo + valor; // fazer com que transacao seja possivel para outros
        }
    }

    public void levantamento(double valor) throws Exception {
        if (valor > 400) {
            throw new Exception("Erro: O levantamento excede o limite de 400 euros diários."); // pôr o limite /dia
                                                                                               // através do historico
                                                                                               // associado à Account
        } else {
            this.saldo = this.saldo - valor;
        }
    }

    public double depositar(double valor) throws DepositoFalhadoException {

        this.saldo = saldo + valor;

        return saldo;
    }


}

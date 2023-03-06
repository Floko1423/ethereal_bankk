package com.bnp.ethereal_bankkk.business_model;

import java.util.LinkedList;
import java.util.Random;

import com.bnp.ethereal_bankkk.business_model.Exceptions.DepositoFalhadoException;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public abstract class Card {

    // Cliente cliente; // parametro desnecessario visto que clientes na mesma conta
    // podem ter acesso aos mesmos cartoes se souberem o pin
    // Conta conta;
    @EmbeddedId
    @GeneratedValue
    private Long id_conta;
    String id;
    String pin;
    int val_dia;
    double saldo;

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

    public String auto_pin() { // fazer com que este método invoque outro que permita ao cliente mudar na 1ª
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

    public void transacao1(Object... args) {

        LinkedList<Object> params = new LinkedList<>(); // aqui pomos 1º o tipo de objecto que vai fazer a transação
                                                        // (conta/cartao) e depois o valor
        // dependente do tipo de objecto vamos buscar o int (num_conta / id) - mostrar
        // contas do cliente/cartoes
        for (Object arg : args) {
            switch (arg.getClass().getSimpleName()) {
                case "Conta":

                case "Cartao":

            }

        }
    }

    public void transacao(double valor) throws Exception { // fazer method overload em conta para conseguir transferir
                                                           // para outras contas

        if (valor > 200) {
            throw new Exception("Erro: transacao excede o limite de 200 euros.");
        } else if (valor > saldo) {
            throw new Exception("Erro: transacao não pode exceder o saldo da conta"); // este saldo tem de ser o saldo
                                                                                      // total da conta
        } else {
            saldo = saldo + valor; // fazer com que transacao seja possivel para outros
        }
    }

    public void levantamento(double valor) throws Exception {
        if (valor > 400) {
            throw new Exception("Erro: O levantamento excede o limite de 400 euros diários."); // pôr o limite /dia
                                                                                               // através do historico
                                                                                               // associado à conta
        } else {
            this.saldo = this.saldo - valor;
        }
    }

    public double depositar(double valor) throws DepositoFalhadoException {

        this.saldo = saldo + valor;

        return saldo;
    }


}

package com.bnp.ethereal_bank.model;

import java.util.LinkedList;

public class Transactions {
    
    public void transacao1(Object... args) {

        LinkedList<Object> params = new LinkedList<>(); // aqui pomos 1º o tipo de objecto que vai fazer a transação
                                                        // (Account/Card) e depois o valor
        // dependente do tipo de objecto vamos buscar o int (num_Account / id) - mostrar
        // Accounts do Client/cards
        for (Object arg : args) {
            switch (arg.getClass().getSimpleName()) {
                case "Account":
                // params.add(Account.getAccount_num());
                // params.add(Account.getClient().getId());
                // params.add(Account.getAccountType().toString());
                // params.add(Account.getBalance());
                break;
                case "Card":
                // params.add(Card.getCardId());
                // params.add(Card.getAccount().getAccountId());
                // params.add(Card.getCardType().toString());
                // params.add(Card.getLimit());
                // params.add(Card.getBalance());
                break;
            }

        }
    }

    public void transacao(boolean valor) throws Exception { // fazer method overload em Account para conseguir transferir
                                                           // para outras Accounts

        if (valor) {
            throw new Exception("Erro: transacao excede o limite de 200 euros.");
        } else if (valor) {
            throw new Exception("Erro: transacao não pode exceder o saldo da Account"); // este saldo tem de ser o saldo
                                                                                      // total da Account
        } else {
            //saldo = saldo + valor; // fazer com que transacao seja possivel para outros
        }
    }


}

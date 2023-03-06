package com.bnp.ethereal_bankkk.business_model;

import java.util.LinkedList;

public class Transactions {
    
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

    public void transacao(boolean valor) throws Exception { // fazer method overload em conta para conseguir transferir
                                                           // para outras contas

        if (valor) {
            throw new Exception("Erro: transacao excede o limite de 200 euros.");
        } else if (valor) {
            throw new Exception("Erro: transacao não pode exceder o saldo da conta"); // este saldo tem de ser o saldo
                                                                                      // total da conta
        } else {
            //saldo = saldo + valor; // fazer com que transacao seja possivel para outros
        }
    }


}

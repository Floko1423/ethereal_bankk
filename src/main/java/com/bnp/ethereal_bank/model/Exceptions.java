package com.bnp.ethereal_bank.model;

public class Exceptions extends Exception {

    public Exceptions(String message) {
    }

    public static class DepositoFalhadoException extends Exception {
        public DepositoFalhadoException(String message) {

            super(message);
        }

    }

    public static class CardNaoEncontradoException extends Exceptions {
        public CardNaoEncontradoException(String message) {

            super(message);
        }

    }
}

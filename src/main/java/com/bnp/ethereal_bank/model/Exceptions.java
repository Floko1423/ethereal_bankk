package com.bnp.ethereal_bankkk.business_model;

public class Exceptions extends Exception {

    public Exceptions(String message) {
    }

    public static class DepositoFalhadoException extends Exception {
        public DepositoFalhadoException(String message) {

            super(message);
        }

    }

    public static class CartaoNaoEncontradoException extends Exceptions {
        public CartaoNaoEncontradoException(String message) {

            super(message);
        }

    }
}

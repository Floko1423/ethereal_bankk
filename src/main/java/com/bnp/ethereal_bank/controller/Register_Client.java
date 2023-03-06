package com.bnp.ethereal_bank.controller;

public class Register_Client {
    private String name;
    private String senha;

    
    public Register_Client(String name, String senha) {
        this.name = name;
        this.senha = senha;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

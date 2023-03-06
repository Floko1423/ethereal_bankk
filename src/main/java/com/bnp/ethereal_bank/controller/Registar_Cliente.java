package com.bnp.ethereal_bank.controller;

public class Registar_Cliente {
    private String name;
    private String senha;

    
    public Registar_Cliente(String name, String senha) {
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

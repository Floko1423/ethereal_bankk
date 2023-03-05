package com.bnp.ethereal_bank.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private UUID id;

    @Column(name="nif")
    private String NIF;

    @Column(name="senha")
    private String senha;

    @Column(name="name")
    private String nome;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Client(UUID id, String nIF, String senha, String nome) {
		this.id = id;
		this.NIF = nIF;
		this.senha = senha;
		this.nome = nome;
	}

	public Client() {
	}
    
	public Client(String nIF, String senha, String nome) {
		
		this.NIF = nIF;
		this.senha = senha;
		this.nome = nome;
	}

}

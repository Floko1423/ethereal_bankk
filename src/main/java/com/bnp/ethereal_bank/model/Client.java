package com.bnp.ethereal_bank.model;

import java.util.HashMap;
import java.util.LinkedList;
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

	@Column(name="id")
    @Id
    private UUID id;

    @Column(name="nif")
    private String NIF;

    @Column(name="senha")
    private String senha;

    @Column(name="name")
    private String name;

	@Column(name="data_na")
	private String data_na;

	@Column(name="telefone")
    private String telefone;

	@Column(name="telemovel")
    private String telemovel;

    @Column(name="email")
	private String email; // relatorio mensal para o email?

    @Column(name="name")
	private String profissao;

    @Column(name="contas")
	private HashMap<Account, LinkedList<Cartao>> contas;

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
		return name;
	}

	public void setNome(String nome) {
		this.name = name;

	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData_na() {
		return data_na;
	}

	public void setData_na(String data_na) {
		this.data_na = data_na;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public HashMap<Account, LinkedList<Cartao>> getContas() {
		return contas;
	}

	public void setContas(HashMap<Account, LinkedList<Cartao>> contas) {
		this.contas = contas;
	}

	public Client(UUID id, String NIF, String senha, String name) {
		this.id = id;
		this.NIF = NIF;
		this.senha = senha;
		this.name = name;
	}

	public Client() {
	}
    
	public Client(String NIF, String senha, String name) {
		
		this.NIF = NIF;
		this.senha = senha;
		this.name = name;
	}

}

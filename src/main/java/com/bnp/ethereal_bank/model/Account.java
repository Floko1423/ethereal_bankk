package com.bnp.ethereal_bank.model;

import com.bnp.ethereal_bank.model.Card;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
    private static AtomicInteger last_num_Account = new AtomicInteger(0);
    AtomicInteger num_Account;
    
    @Column(name="id")
    @Id
    private UUID id;
    //private static AtomicInteger Num_Account = new AtomicInteger(100); // Using an AtomicInteger for the account number can be a good approach in a multi-threaded environment where you need to ensure that each account number is unique and that multiple threads do not generate the same account number.
    //private id= 101; //  necessario com o AtomicInt? // para db
    private static AtomicInteger last_account_num = new AtomicInteger(0);

    @Column(name="account_num")
    AtomicInteger account_num;

    @Column(name="titulares")
    LinkedList<Client> holders; // titulares// min 18 anos idade p/ o tit_princ, obrigatório, max 4 tit_sec

    @Column(name="shared_balance")
    double shared_balance; // parâmetro necessário? Senão verificar se saldo>50 (minimo para abrir Account) a partir do metodo para somar o saldo dos cards
    LinkedList<Card> cards; 

    @Column(name="transaction_history")
    LinkedList<String> historico;
    
    

    private Tipo tipo;

   public static AtomicInteger getLast_num_Account() {
        return last_num_Account;
    }


    public static void setLast_num_Account(AtomicInteger last_num_Account) {
        Account.last_num_Account = last_num_Account;
    }


    public void setNum_Account(AtomicInteger num_Account) {
        this.num_Account = num_Account;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public static AtomicInteger getLast_account_num() {
        return last_account_num;
    }


    public static void setLast_account_num(AtomicInteger last_account_num) {
        Account.last_account_num = last_account_num;
    }


    public AtomicInteger getAccount_num() {
        return account_num;
    }


    public void setAccount_num(AtomicInteger account_num) {
        this.account_num = account_num;
    }


    public LinkedList<Client> getHolders() {
        return holders;
    }


    public void setHolders(LinkedList<Client> holders) {
        this.holders = holders;
    }


    public double getShared_balance() {
        return shared_balance;
    }


    public void setShared_balance(double shared_balance) {
        this.shared_balance = shared_balance;
    }


    public LinkedList<Card> getCards() {
        return cards;
    }


    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }


    public LinkedList<String> getHistorico() {
        return historico;
    }


    public void setHistorico(LinkedList<String> historico) {
        this.historico = historico;
    }


  public enum Tipo {
      corrente,
      poupanca
    }

public  AtomicInteger getNum_Account() {
    return num_Account;
}


    public Account() {
        
        
        num_Account= new AtomicInteger(last_num_Account.incrementAndGet());
        cards = new LinkedList<Card>();
        holders = new LinkedList<Client>();

        //int nextNumAccount = Num_Account.incrementAndGet();
        //this.Num_Account.set(nextNumAccount);
    
        //set this.Client= tit_principal no momento de criação da Account
        //this.id = Num_Account.getAndIncrement();
    }


    public LinkedList<Card> getcards() {
        return cards;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Account other = (Account) obj;
        return this.num_Account == other.num_Account;   
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.num_Account.intValue(); // nao posso mudar o hash para AtomicInteger senao afeta outros metodos como o equals()
        return hash;
    }


   
}

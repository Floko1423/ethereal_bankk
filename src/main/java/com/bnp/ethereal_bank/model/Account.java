package com.bnp.ethereal_bank.model;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Account {
    private static AtomicInteger last_num_conta = new AtomicInteger(0);
    AtomicInteger Num_conta;
    
    @EmbeddedId
    @GeneratedValue
    private Long id_conta;
    //private static AtomicInteger Num_conta = new AtomicInteger(100); // Using an AtomicInteger for the account number can be a good approach in a multi-threaded environment where you need to ensure that each account number is unique and that multiple threads do not generate the same account number.
    //private id= 101; //  necessario com o AtomicInt? // para db
    

    LinkedList<Client> titulares; // min 18 anos idade p/ o tit_princ, obrigatório, max 4 tit_sec

    double saldo_partilhado; // parâmetro necessário? Senão verificar se saldo>50 (minimo para abrir conta) a
                             // partir do metodo para somar o saldo dos cartoes
    LinkedList<Card> cartoes; // usar map visto que hashmap pode guardar valores duplicados
    LinkedList<String> historico;
    

    //private Tipo tipo;

   /*  public enum Tipo {
      corrente,
      poupanca
    }
*/
public  AtomicInteger getNum_conta() {
    return Num_conta;
}


    public Account() {
        
        
        Num_conta= new AtomicInteger(last_num_conta.incrementAndGet());
        cartoes = new LinkedList<Card>();
        titulares = new LinkedList<Client>();

        //int nextNumConta = Num_conta.incrementAndGet();
        //this.Num_conta.set(nextNumConta);
    
        //set this.Client= tit_principal no momento de criação da conta
        //this.id = Num_conta.getAndIncrement();
    }


    

    public LinkedList<Card> getCartoes() {
        return cartoes;
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
        return this.Num_conta == other.Num_conta;   
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.Num_conta.intValue(); // nao posso mudar o hash para AtomicInteger senao afeta outros metodos como o equals()
        return hash;
    }




    



    public static void addCartao(Card cartao) {
        
    }


    


   
}

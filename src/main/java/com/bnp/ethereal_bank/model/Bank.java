package com.bnp.ethereal_bank.model;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.util.random.RandomGenerator;

//import com.bnp.ethereal_bank.model.Client.ClientBuilder;
import com.bnp.ethereal_bank.services.*;
import com.bnp.ethereal_bank.services.Service_DB;
import com.bnp.ethereal_bank.services.Service_Security;

public class Bank {
  // fazer um singleton com static block de modo a haver apenas uma instancia 

  public final static Bank obj;
  private Service_DB services_db;
  // private Service_Generic services_gen;
  private Service_Security guardian;
  private static ArrayList<Client> clients = new ArrayList<Client>(20);
  public static ArrayList<Card> cards = new ArrayList<Card>(20);
  public static ArrayList<Account> accounts = new ArrayList<Account>(20);

  // guardiao = new Servicos_Seguranca();
  // instancio aqui as ArrayLists e depois crio um método no main
  // que popula o banco
  private Bank() {
  }

  static {
    try {
      obj = new Bank();

    } catch (Exception e) {
      throw new RuntimeException("Algo não correu bem na criação do banco");
    }

  }

  public static Bank getInstance() {
    return obj;

  }

  // public static ArrayList<Client> Clients_dados() {
  // Bank bk= new Bank();
  // ArrayList<Client> Clients = Bank.clients;

  // //Client c1 = new Client.ClientBuilder("1234 ", "4321", "fc").build();

  // Client c2 = new Client.ClientBuilder("12345", "12345", "mc")
  // .date_ob("23")
  // .email("mc@")
  // .profession("psicologa")
  // .fixedPhone("917717182")
  // .mobilePhone("917849391")

  // .build();
  // //Clients.add(c1);
  // Clients.add(c2);
  // c2.create_Account(c2);
  // return Clients;

  // }
  

  public static ArrayList<Client> getClients() {
    return clients;
  }

  /*
   * public ArrayList<Card> cards_dados() {
   * ArrayList<Card> cards = new ArrayList<Card>(20);
   * Card cart1_c = new Card_Credito("1234", "4321", 2, 50, 50);
   * Card cart2_d = new Card_Debito("4321", "1234", 5, 60);
   * cards.add(cart1_c);
   * cards.add(cart2_d);
   * return cards;
   * }
   */

  /*
   * public HashMap<Integer, Account> dados_Accounts() {
   * HashMap<Integer, Account> Accounts = new HashMap<Integer, Account>();
   * Account Account1 = new Account("1244", "fc", "fs", 50);
   * Accounts.put(1,Account1);
   * return Accounts;
   * }
   */

}

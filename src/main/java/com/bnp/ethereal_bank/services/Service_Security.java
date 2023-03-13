package com.bnp.ethereal_bank.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.bnp.ethereal_bank.model.Bank; // alterar
import com.bnp.ethereal_bank.model.Card;
import com.bnp.ethereal_bank.model.Client;
/*One common approach is to use a "session-per-request" pattern, 
where a new Hibernate Session object is created at the beginning of each web request, and then closed at the end of the request.
This ensures that each web request is isolated within its own Session object, and helps to prevent issues with concurrency and data
consistency. */
import com.bnp.ethereal_bank.model.Account;


final public class Service_Security {

   private final static Set<Sessao> sessoes; // Preciso de guardar com o hibernate ou ele faz isso por mim? se calhar
                                             // dict para guardar a Sessao-Client (desnecessario se a sessao tiver um
                                             // campo Client?)
   final static Service_Security guardiao;
   // private static final receptor(); //recebe pedidos do banco para iniciar

   private Service_Security() {

   }

   static {

      guardiao = new Service_Security();
      sessoes = new HashSet();
   }

   public void autenticar(Client Client) {
      Sessao sessao = new Sessao(Client);
      Client.setAutorizado();
      sessoes.add(sessao);
   }

   public boolean autenticar(Optional<String> nif, Optional<String> password, Optional<AtomicInteger> num_Account,
         Optional<String> pin_Card) { // Optional<String> parameters to allow for null values to be passed in
      // check if any authentication parameter was provided

      ArrayList<Client> Clients = Bank.getClients();
      if (!nif.isPresent() && !num_Account.isPresent() && !pin_Card.isPresent()) {
         return false; // no authentication parameter provided
      }
      // ADICIONAR password NOS PARAMETROS OPTIONAL(OU NO IF/ELSE STATEMENT NOS
      // DIFERENTES CASOS EX: getNum_Account.equals(.. &&get.password().equals()...)
  
      Optional<Client> Client = Optional.empty();
      Optional<Account> Account = Optional.empty();
      Optional<Card> Card = Optional.empty();
      if (nif.isPresent() && password.isPresent()) { // LOGIN: cria sessao e autoriza o Client (acesso às
                                                     // Accounts(create), )
         Client = Bank.getClients()
               .stream()
               .filter(c -> c.getnif().equals(nif.get()) && c.getpassword().equals(password)).findFirst();
         guardiao.autenticar(Client.get());

         // Client.get(autorizado);
      } else if (num_Account.isPresent() && Client.get().getAutorizado().isPresent()) { // check se o Client está
                                                                                        // autenticado; senão pedir para
                                                                                        // autenticar
         // stream das sessoes- ver se tem uma sessao com este Client
         // Client= guardiao.sessoes.stream().filter(s-> s.ge)
         // Client.corresponde(this.Client);

         // Account = Client.getAccounts().stream().filter(co ->
         // Account.getNum_Account().equals(num_Account.get()) )

         /*
          * Client = Banco.getClients().stream().filter(c -> (c.getAccounts().keySet()
          * .for(Account Account: Accounts){
          * return Account.getNum_Account();
          * }.equals(num_Account.get())))
          */
         // .forEach( -> { AtomicInteger Num_Account =
         // this.keySet.get(Account).getNum_Account();}))
         // .findFirst();
      } else if (pin_Card.isPresent()) {
         // Client = Bank.getClients().stream().filter(c ->
         // c.getAccounts().values().Accountin(pin_Card.get())).findFirst();
      }

      if (Client.isPresent()) {
         // authenticate the client using their password
         // for example:
         // if (Client.get().getpassword().equals(password)) {
         // return true;
         // } else {
         // return false;
         // }
         return true; // authenticated successfully
      } else {
         return false; // client not found
      }

      // overloading or switch case method para autenticar com nif/num_Account/id
      // Card...

      // Banco.Clients.add(Client);
      // vai aos Clients ver se o nif associado inserido está associado à password
   }

   private class Sessao {

      // private final int id; //?
      private final Client Client;
      LinkedList<Object> info_sessao; // guarda nif Client, start time/exp time, tempo de sessao,

      public Sessao(Client Client) {
         this.Client = Client;

         ;
      }

      public boolean corresponde(Client Client) {
         if (Client.equals(this.Client)) { // equals devolve o .equals do nif portanto posso escrever assim
            return true;
         } else
            return false;
      }

      public void autorizar(Optional<Client> Client) {
         this.Client.setAutorizado();
         System.out.println("O Client foi autorizado com sucesso");
      }
   }

}

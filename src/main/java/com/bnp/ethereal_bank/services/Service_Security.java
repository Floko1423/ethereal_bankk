package com.bnp.ethereal_bankkk.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.bnp.ethereal_bankkk.business_model.Banco; // alterar
import com.bnp.ethereal_bankkk.business_model.Cartao;
import com.bnp.ethereal_bankkk.business_model.Client;
/*One common approach is to use a "session-per-request" pattern, 
where a new Hibernate Session object is created at the beginning of each web request, and then closed at the end of the request.
This ensures that each web request is isolated within its own Session object, and helps to prevent issues with concurrency and data
consistency. */
import com.bnp.ethereal_bankkk.business_model.Conta;

//- anotacao para Spring

final public class Service_Security {

   private final static Set<Sessao> sessoes; //Preciso de guardar com o hibernate ou ele faz isso por mim? se calhar dict para guardar a Sessao-Client (desnecessario se a sessao tiver um campo Client?)
   final static Service_Security guardiao;
   // private static final receptor(); //recebe pedidos do banco para iniciar

   private Service_Security() {
      
   }
   static {  
      
      guardiao = new Service_Security();
      sessoes= new HashSet();
   }

   public void autenticar(Client Client) {
      Sessao sessao= new Sessao(Client);
      Client.setAutorizado();
      sessoes.add(sessao);
     }
    
   public boolean autenticar(Optional<String> NIF, Optional<String> senha, Optional<AtomicInteger> num_conta, Optional<String> pin_cartao) { //Optional<String> parameters to allow for null values to be passed in
      // check if any authentication parameter was provided

      

      ArrayList<Client> Clients = Banco.getClients();
      if (!NIF.isPresent() && !num_conta.isPresent() && !pin_cartao.isPresent()) {
          return false; // no authentication parameter provided
      }
  // ADICIONAR SENHA NOS PARAMETROS OPTIONAL(OU NO IF/ELSE STATEMENT NOS DIFERENTES CASOS EX: getNum_conta.equals(.. &&get.senha().equals()...)
      // find the client based on the authentication parameter
      //Optional<Client> Client = Optional.empty();
      Optional<Client> Client = Optional.empty();
      Optional<Account> conta = Optional.empty();
      Optional<Cartao> cartao = Optional.empty();
      if (NIF.isPresent() && senha.isPresent()){ // LOGIN: cria sessao e autoriza o Client (acesso às contas(criar), )
          Client = Banco.getClients()
          .stream()
          .filter(c -> c.getNIF().equals(NIF.get()) && c.getSenha().equals(senha)).findFirst();
          guardiao.autenticar(Client.get());
          
          //Client.get(autorizado);
      } else if (num_conta.isPresent() && Client.get().getAutorizado().isPresent() ){ // check se o Client está autenticado; senão pedir para autenticar
         // stream das sessoes- ver se tem uma sessao com este Client
         //Client= guardiao.sessoes.stream().filter(s-> s.ge)
         //Client.corresponde(this.Client);
         
         //conta = Client.getContas().stream().filter(co -> conta.getNum_conta().equals(num_conta.get()) )

         /*Client = Banco.getClients().stream().filter(c -> (c.getContas().keySet()
          .for(Conta conta: contas){
            return conta.getNum_conta();
          }.equals(num_conta.get()))) */
          //.forEach(  -> { AtomicInteger Num_Conta = this.keySet.get(Conta).getNum_conta();}))
          //.findFirst();
      } else if (pin_cartao.isPresent()) {
         Client = Banco.getClients().stream().filter(c -> c.getContas().values().contains(pin_cartao.get())).findFirst();
      }
  
      if (Client.isPresent()) {
          // authenticate the client using their password
          // for example:
          // if (Client.get().getSenha().equals(senha)) {
          //     return true;
          // } else {
          //     return false;
          // }
          return true; // authenticated successfully
      } else {
          return false; // client not found
      }
  
  
       //overloading or switch case method para autenticar com NIF/num_conta/id cartao...
      
      //Banco.Clients.add(Client);
      // vai aos Clients ver se o NIF associado inserido está associado à senha
   }

   

   private class Sessao {

      //private final int id; //?
      private final Client Client;
      LinkedList<Object> info_sessao; //guarda NIF Client, start time/exp time, tempo de sessao, 

      public Sessao(Client Client) {
         this.Client=Client;

         ;
     }
     
     public boolean corresponde(Client Client) {
      if (Client.equals(this.Client)) { // equals devolve o .equals do NIF portanto posso escrever assim
         return true;
      }
      else return false;
     }
     public void autorizar(Optional<Client> Client) {
      this.Client.setAutorizado();
      System.out.println("O Client foi autorizado com sucesso");
     }
   }

}

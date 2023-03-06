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
import com.bnp.ethereal_bankkk.business_model.Cliente;
/*One common approach is to use a "session-per-request" pattern, 
where a new Hibernate Session object is created at the beginning of each web request, and then closed at the end of the request.
This ensures that each web request is isolated within its own Session object, and helps to prevent issues with concurrency and data
consistency. */
import com.bnp.ethereal_bankkk.business_model.Conta;

//- anotacao para Spring

final public class Service_Security {

   private final static Set<Sessao> sessoes; //Preciso de guardar com o hibernate ou ele faz isso por mim? se calhar dict para guardar a Sessao-Cliente (desnecessario se a sessao tiver um campo cliente?)
   final static Service_Security guardiao;
   // private static final receptor(); //recebe pedidos do banco para iniciar

   private Service_Security() {
      
   }
   static {  
      
      guardiao = new Service_Security();
      sessoes= new HashSet();
   }

   public  void autenticar(Cliente cliente) {
      Sessao sessao= new Sessao(cliente);
      cliente.setAutorizado();
      sessoes.add(sessao);
     }
    
   public boolean autenticar(Optional<String> NIF, Optional<String> senha, Optional<AtomicInteger> num_conta, Optional<String> pin_cartao) { //Optional<String> parameters to allow for null values to be passed in
      // check if any authentication parameter was provided

      

      ArrayList<Cliente> clientes = Banco.getClientes();
      if (!NIF.isPresent() && !num_conta.isPresent() && !pin_cartao.isPresent()) {
          return false; // no authentication parameter provided
      }
  // ADICIONAR SENHA NOS PARAMETROS OPTIONAL(OU NO IF/ELSE STATEMENT NOS DIFERENTES CASOS EX: getNum_conta.equals(.. &&get.senha().equals()...)
      // find the client based on the authentication parameter
      //Optional<Cliente> cliente = Optional.empty();
      Optional<Cliente> cliente = Optional.empty();
      Optional<Account> conta = Optional.empty();
      Optional<Cartao> cartao = Optional.empty();
      if (NIF.isPresent() && senha.isPresent()){ // LOGIN: cria sessao e autoriza o cliente (acesso às contas(criar), )
          cliente = Banco.getClientes()
          .stream()
          .filter(c -> c.getNIF().equals(NIF.get()) && c.getSenha().equals(senha)).findFirst();
          guardiao.autenticar(cliente.get());
          
          //cliente.get(autorizado);
      } else if (num_conta.isPresent() && cliente.get().getAutorizado().isPresent() ){ // check se o cliente está autenticado; senão pedir para autenticar
         // stream das sessoes- ver se tem uma sessao com este cliente
         //cliente= guardiao.sessoes.stream().filter(s-> s.ge)
         //cliente.corresponde(this.cliente);
         
         //conta = cliente.getContas().stream().filter(co -> conta.getNum_conta().equals(num_conta.get()) )

         /*cliente = Banco.getClientes().stream().filter(c -> (c.getContas().keySet()
          .for(Conta conta: contas){
            return conta.getNum_conta();
          }.equals(num_conta.get()))) */
          //.forEach(  -> { AtomicInteger Num_Conta = this.keySet.get(Conta).getNum_conta();}))
          //.findFirst();
      } else if (pin_cartao.isPresent()) {
         cliente = Banco.getClientes().stream().filter(c -> c.getContas().values().contains(pin_cartao.get())).findFirst();
      }
  
      if (cliente.isPresent()) {
          // authenticate the client using their password
          // for example:
          // if (cliente.get().getSenha().equals(senha)) {
          //     return true;
          // } else {
          //     return false;
          // }
          return true; // authenticated successfully
      } else {
          return false; // client not found
      }
  
  
       //overloading or switch case method para autenticar com NIF/num_conta/id cartao...
      
      //Banco.clientes.add(cliente);
      // vai aos clientes ver se o NIF associado inserido está associado à senha
   }

   

   private class Sessao {

      //private final int id; //?
      private final Cliente cliente;
      LinkedList<Object> info_sessao; //guarda NIF cliente, start time/exp time, tempo de sessao, 

      public Sessao(Cliente cliente) {
         this.cliente=cliente;

         ;
     }
     
     public boolean corresponde(Cliente cliente) {
      if (cliente.equals(this.cliente)) { // equals devolve o .equals do NIF portanto posso escrever assim
         return true;
      }
      else return false;
     }
     public void autorizar(Optional<Cliente> cliente) {
      this.cliente.setAutorizado();
      System.out.println("O cliente foi autorizado com sucesso");
     }
   }

}

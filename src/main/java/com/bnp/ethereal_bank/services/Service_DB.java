package com.bnp.ethereal_bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.repository.Client_Repository;

@Service
public class Service_DB {

    private final JdbcTemplate jdbcTemplate;
    private final Client_Repository repository;

    @Autowired
    public Service_DB(JdbcTemplate jdbcTemplate, Client_Repository repository) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
    }

    public void saveSomething(String data) {
        jdbcTemplate.update("INSERT INTO clients (data) VALUES (?)", data);
    }

    public List<Client> getUsers() {
        List<Client> lista = new ArrayList<>();

        lista=repository.findAll();
        System.out.println(repository.findAll());

        return repository.findAll();
        
    }

    public void createUser(String name, String senha) {
        Client c1 = new Client(UUID.randomUUID(),"1234", senha, name);
        
        repository.save(c1);

    }

    public void login(String senha, String NIF ) {
        /* verificar que NIF existe na DB 
        caso exista: 
            verificar que pass submetida coincide com a pass da DB
            extra: fazer hash da password 
                se for: metodo de autenticaçao -> java spring authorization with token -> token valido: vai para outra pagina de acesso geral 
        se nao existe:    
            catch(e) quando 404 (...) message: ... enviar @response body 
        
        */
    }
}

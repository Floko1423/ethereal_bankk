package com.bnp.ethereal_bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bnp.ethereal_bank.authentication.Register_Request;
import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.model.Role;
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

        lista = repository.findAll();
        System.out.println(repository.findAll());

        return repository.findAll();

    }

    public void  register2(Register_Request request) {
        Client.Builder builder = new Client.Builder();
        Client client = builder
                // se nao der crio um builder
                .setName(request.getName())
                .setEmail(request.getEmail())
                // .build(); // encode password before saving it to the db, temos de injetar o
                // servico de passenc
                .setPassword((request.getPassword())) //passwordEncoder.encode
                //.setRole(Role.USER)
                .build();

                Client client1= client;


        repository.save(client1);
    }
    public void createUser(String name, String password) {
        Client c1 = new Client(UUID.randomUUID(), "1234", password, name);

        repository.save(c1);

    }

    public void login1(String password, String nif) {
        /*
         * verificar que nif existe na DB
         * caso exista:
         * verificar que pass submetida coincide com a pass da DB
         * extra: fazer hash da password
         * se for: metodo de autenticaçao -> java spring authorization with token ->
         * token valido: vai para outra pagina de acesso geral
         * se nao existe:
         * catch(e) quando 404 (...) message: ... enviar @response body
         * 
         */
    }

    public String login(String nif, String password) {
        System.out.println("nif" + nif + "repository" + repository);
        Client client = repository.findByNif(nif);

        if (client == null || !client.getpassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = generateToken(client);

        return token;
    }

    private String generateToken(Client client) {
        // return token
        return "";
        // Implement token generation logic here
    }
}

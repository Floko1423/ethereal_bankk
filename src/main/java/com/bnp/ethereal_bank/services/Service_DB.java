package com.bnp.ethereal_bank.services;

import java.util.ArrayList;
import java.util.List;

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

    public void createUser(String name) {
        Client c1 = new Client("1234", "4321", name);
        repository.save(c1);

    }
}

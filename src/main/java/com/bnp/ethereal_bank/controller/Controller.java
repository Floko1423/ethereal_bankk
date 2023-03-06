package com.bnp.ethereal_bank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.services.Service_DB;

@RestController
public class Controller {

    Service_DB service;

    public Controller(Service_DB service) {
		this.service = service;
	}

    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/user")
    public List<Client> getUsers() {
        return service.getUsers();

    }
    @CrossOrigin(origins= "http://localhost:3000")
    @PostMapping("/addUser")
    public void addUser(@RequestBody Registar_Cliente request) {

        //service.createUser(name, senha);
        service.createUser(request.getName(), request.getSenha());

         // nao se usa return em posts (estou a criar info, nao a ir buscar)

    }
    
}

package com.bnp.ethereal_bank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/user")
    public List<Client> getUsers() {
        return service.getUsers();

    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name) {

        service.createUser(name);

        return "lel";  // nao se usa return em posts (estou na criar info, nao a ir buscar)

    }
    
}

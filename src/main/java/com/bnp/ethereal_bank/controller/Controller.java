package com.bnp.ethereal_bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bnp.ethereal_bank.authentication.Authentication_Service;
//import com.bnp.ethereal_bank.authentication.Authentication_Service;
import com.bnp.ethereal_bank.authentication.Register_Request;
import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.services.Service_DB;

@RestController
public class Controller {

    private Authentication_Service service1;

    @Autowired
    Service_DB service;

    public Controller(Service_DB service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000") // metodo de teste so para retornar os users todos, tenho de atualizar para getuser para autenticação com nif e password                                                  
    @GetMapping("/user")
    public ResponseEntity<List<Client>> getUsers() {
        List<Client> users = service.getUsers();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // return HTTP 204 No Content if list is empty
        } else {
            return ResponseEntity.ok(users); // return HTTP 200 OK with the list of users
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody Register_Request request) {
        System.out.println("entered addUser");
        try {
            service.register2(request);
            service.createUser(request.getName(), request.getPassword());
           return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");  
                                                                                                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user: " + e.getMessage()); // return HTTP 404 bad req
        
        }
    }
    // verificar na BD que nao tem um nif igual, mandar exception de erro se tiver



}

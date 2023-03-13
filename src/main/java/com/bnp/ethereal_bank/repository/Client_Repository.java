package com.bnp.ethereal_bank.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnp.ethereal_bank.model.Client;

@Repository
public interface Client_Repository extends JpaRepository<Client, UUID> {

    Optional<Client> findByName(String name);

    Client findByPassword(String password);
    //Client findByEmail(String email);
    Optional<Client> findByEmail(String email);

    @Override
    List<Client> findAll();

    Client findByNif(String nif);

    //void saveUser(Object user);

}

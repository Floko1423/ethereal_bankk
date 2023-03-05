package com.bnp.ethereal_bank.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnp.ethereal_bank.model.Client;


@Repository
public interface Client_Repository extends JpaRepository<Client, UUID>{

    
    @Override
    List<Client> findAll();

}

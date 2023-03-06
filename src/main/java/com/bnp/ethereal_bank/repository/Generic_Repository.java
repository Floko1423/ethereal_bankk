package com.bnp.ethereal_bank.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Generic_Repository<T,UUID> {

   List<T> findAll();
   T findById(UUID id);
   void save(T entity);
   void deleteById(UUID id);
   void findByName(String name);

}

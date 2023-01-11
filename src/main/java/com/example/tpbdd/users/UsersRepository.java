package com.example.tpbdd.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepository extends MongoRepository<Users, Long> {
    Users findAllByUsername(String username);
    Users findById(String id);
    List<Users> findAllByDescriptionContaining(String description);
    List<Users> findAll();

}

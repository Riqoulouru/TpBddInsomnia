package com.example.tpbdd.log;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogsRepository extends MongoRepository<Logs, Long> {
    Logs findByUserId(String userId);

    List<Logs> findAllByUserId(String userId);

}

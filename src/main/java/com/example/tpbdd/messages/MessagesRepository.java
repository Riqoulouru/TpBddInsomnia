package com.example.tpbdd.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Messages, Long> {

    Messages findByFrom(String userId);

    List<Messages> findAllByFromAndTo(String user1, String user2);

}

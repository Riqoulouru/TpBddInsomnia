package com.example.tpbdd.posts;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostsRepository extends MongoRepository<Posts, Long> {
    List<Posts> findAllByUserId(String userId);

}

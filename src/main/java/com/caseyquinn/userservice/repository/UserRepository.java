package com.caseyquinn.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.caseyquinn.userservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

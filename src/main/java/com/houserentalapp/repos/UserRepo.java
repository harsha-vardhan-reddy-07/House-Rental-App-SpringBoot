package com.houserentalapp.repos;

import com.houserentalapp.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserModel, String> {
 UserModel findByEmail(String email);
}

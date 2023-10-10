package com.houserentalapp.repos;

import com.houserentalapp.models.PropertiesModel;
import com.houserentalapp.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertiesRepo extends MongoRepository<PropertiesModel, String> {
    PropertiesModel findByOwnerId(String ownerId);
}

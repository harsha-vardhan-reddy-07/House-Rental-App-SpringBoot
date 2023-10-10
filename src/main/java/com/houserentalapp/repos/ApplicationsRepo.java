package com.houserentalapp.repos;

import com.houserentalapp.models.ApplicationModel;
import com.houserentalapp.models.PropertiesModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationsRepo extends MongoRepository<ApplicationModel, String> {
    ApplicationModel findByApplicantId(String applicantId);
}

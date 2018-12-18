package com.api.dse.poc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.api.dse.poc.model.AlertsP;
public interface AlertsPMRepo extends MongoRepository<AlertsP,String>{

}

package com.api.dse.alerts.poc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.api.dse.alerts.poc.model.AlertsP;
public interface AlertsPMRepo extends MongoRepository<AlertsP,String>{

}

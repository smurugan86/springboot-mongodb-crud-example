package net.guides.springboot.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.PayEvent;



@Repository
public interface PayEventRepository extends MongoRepository<PayEvent, Long>{

}

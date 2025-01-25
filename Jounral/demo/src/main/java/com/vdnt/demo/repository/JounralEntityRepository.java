package com.vdnt.demo.repository;

import com.vdnt.demo.entity.JounralEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JounralEntityRepository extends MongoRepository<JounralEntity, ObjectId> {

}

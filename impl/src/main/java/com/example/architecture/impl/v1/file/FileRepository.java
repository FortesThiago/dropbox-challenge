package com.example.architecture.impl.v1.file;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository <FileEntity, String>{
}

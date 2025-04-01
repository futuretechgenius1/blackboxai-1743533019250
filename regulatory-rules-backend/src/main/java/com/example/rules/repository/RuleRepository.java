package com.example.rules.repository;

import com.example.rules.model.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends MongoRepository<Rule, String> {
    // MongoRepository provides basic CRUD operations
    // We can add custom query methods here if needed
}
package com.aidtecnologia.api.lang;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LangRepository extends MongoRepository<Lang, String> {
    List<Lang> findByOrderByRanking();
}

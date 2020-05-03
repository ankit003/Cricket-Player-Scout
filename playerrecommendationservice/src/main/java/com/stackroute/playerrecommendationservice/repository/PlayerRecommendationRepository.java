package com.stackroute.playerrecommendationservice.repository;

import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRecommendationRepository extends MongoRepository<PlayerRecommendation, Long> {

    public List<PlayerRecommendation> findByPlayerId(Long playerId);

    public void deleteByPlayerId(Long playerId);
}

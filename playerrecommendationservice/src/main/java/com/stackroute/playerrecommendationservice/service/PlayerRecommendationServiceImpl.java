package com.stackroute.playerrecommendationservice.service;

import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
import com.stackroute.playerrecommendationservice.repository.PlayerRecommendationRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerRecommendationServiceImpl implements PlayerRecommendationService{

    private final PlayerRecommendationRepository playerRecommendationRepository;

    public PlayerRecommendationServiceImpl(PlayerRecommendationRepository playerRecommendationRepository) {
        this.playerRecommendationRepository = playerRecommendationRepository;
    }

    @Override
    public boolean addPlayerRecommendation(PlayerRecommendation playerRecommendation) {
        if(playerRecommendationRepository.findByPlayerId(playerRecommendation.playerId).size() <= 0) {
            PlayerRecommendation playerRecommendation1 = new PlayerRecommendation(playerRecommendation.playerId, playerRecommendation.playerName, (long) 1);
            playerRecommendationRepository.insert(playerRecommendation1);
        } else {
            List<PlayerRecommendation> storedRecommendation = playerRecommendationRepository.findByPlayerId(playerRecommendation.playerId);
            long counter = storedRecommendation.get(0).recommendationCounter;
            PlayerRecommendation playerRecommendation1 = new PlayerRecommendation(playerRecommendation.playerId, playerRecommendation.playerName, counter+1);
            playerRecommendationRepository.deleteByPlayerId(playerRecommendation.playerId);
            playerRecommendationRepository.insert(playerRecommendation1);
        }
        if(playerRecommendationRepository.findByPlayerId(playerRecommendation.playerId).size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<PlayerRecommendation> viewPlayerRecommendations() {
        return playerRecommendationRepository.findAll(Sort.by(Sort.Direction.DESC, "recommendationCounter"));
    }
}

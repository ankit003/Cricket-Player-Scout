package com.stackroute.playerrecommendationservice.service;

import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;

import java.util.List;

public interface PlayerRecommendationService {

    boolean addPlayerRecommendation(PlayerRecommendation playerRecommendation);

    List<PlayerRecommendation> viewPlayerRecommendations();


}
package com.stackroute.playerrecommendationservice.model;

import org.springframework.data.annotation.Id;

public class PlayerRecommendation {

    @Id
    public Long playerId;
    public String playerName;
    public Long recommendationCounter;

    public PlayerRecommendation() {}

    public PlayerRecommendation(Long playerId, String playerName, Long recommendationCounter) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.recommendationCounter = recommendationCounter;
    }


    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getRecommendationCounter() {
        return recommendationCounter;
    }

    public void setRecommendationCounter(Long recommendationCounter) {
        this.recommendationCounter = recommendationCounter;
    }

    @Override
    public String toString() {
        return "PlayerRecommendation{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", recommendationCounter=" + recommendationCounter +
                '}';
    }
}

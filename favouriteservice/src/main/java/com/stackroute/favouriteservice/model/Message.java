package com.stackroute.favouriteservice.model;

public class Message {
    private Long playerId;
    private String playerName;
    private Long recommendationCounter;

    public Message(Long playerId, String playerName, Long recommendationCounter) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.recommendationCounter = recommendationCounter;
    }

    public Message() {}

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
        return "Message{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", recommendationCounter=" + recommendationCounter +
                '}';
    }
}

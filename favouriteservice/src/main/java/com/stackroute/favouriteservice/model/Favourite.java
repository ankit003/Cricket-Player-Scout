package com.stackroute.favouriteservice.model;

import org.springframework.data.annotation.Id;

public class Favourite {

    public String userName;
    public long playerId;
    public String playerName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }



    public Favourite() {}

    /*public Favourite(String username, long playerId, String playerName) {
        this.userName = username;
        this.playerId = playerId;
        this.playerName = playerName;
    }*/

    @Override
    public String toString() {
        return "Favourite{" +
                "username='" + userName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}

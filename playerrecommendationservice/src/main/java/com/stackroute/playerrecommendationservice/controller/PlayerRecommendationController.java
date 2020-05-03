package com.stackroute.playerrecommendationservice.controller;

import com.stackroute.playerrecommendationservice.model.Message;
import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@EnableBinding(Sink.class)
@RestController
public class PlayerRecommendationController {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);
    @Autowired
    private PlayerRecommendationService playerRecommendationService;

    /*@Autowired
    public PlayerRecommendationController(PlayerRecommendationService playerRecommendationService) {
        this.playerRecommendationService = playerRecommendationService;
    }*/

    @PostMapping("/playerRecommendation/addPlayerRecommendation")
    public ResponseEntity<Object> addPlayerRecommendationApi(@RequestBody PlayerRecommendation playerRecommendation) {
        ResponseEntity<Object> response = null;
        try {
            if (this.playerRecommendationService.addPlayerRecommendation(playerRecommendation)) {
                response = new ResponseEntity<>(HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @StreamListener(target = Sink.INPUT)
    public void addPlayerRecommendation(Message message) {
        try {
            PlayerRecommendation playerRecommendation = new PlayerRecommendation(message.getPlayerId(), message.getPlayerName(), message.getRecommendationCounter());
            this.playerRecommendationService.addPlayerRecommendation(playerRecommendation);
            System.out.println("Recommendation updated for "+message.getPlayerName());
        } catch (Exception e) {
            System.out.println("Recommendation not updated");
        }
    }

    @GetMapping("/playerRecommendation/viewPlayerRecommendations")
    public ResponseEntity<Object> viewPlayerRecommendations() {
        ResponseEntity<Object> response = null;
        List<PlayerRecommendation> result = null;
        try {
            result = this.playerRecommendationService.viewPlayerRecommendations();
            if (result != null) {
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


}

package com.stackroute.favouriteservice.controller;

import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.model.Message;
import com.stackroute.favouriteservice.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableBinding(Source.class)
public class FavouriteController {


    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private Source source;

    /*@Autowired
    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }*/

    @PostMapping("/favourite/addToFavourite")
    public ResponseEntity<Object> addToFavourite(@RequestBody Favourite favourite) {
        ResponseEntity<Object> response = null;
        try {
            if (this.favouriteService.addFavourite(favourite)) {
                Message message = new Message(favourite.getPlayerId(), favourite.getPlayerName(), (long) 1);
                source.output().send(MessageBuilder.withPayload(message).build());
                response = new ResponseEntity<>(HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    @DeleteMapping("/favourite/removeFavourite/{userName}/{playerId}")
    public ResponseEntity<Object> removeFavourite(@PathVariable String userName, @PathVariable String playerId) {
        ResponseEntity<Object> response = null;
        try {
            if (this.favouriteService.removeFavourite(userName,Long.parseLong(playerId))) {
                response = new ResponseEntity<>(HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    @GetMapping("/favourite/viewFavourites/{userName}")
    public ResponseEntity<Object> viewFavourites(@PathVariable String userName) {
        ResponseEntity<Object> response = null;
        List<Favourite> result = null;
        try {
            result = this.favouriteService.viewFavourites(userName);
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

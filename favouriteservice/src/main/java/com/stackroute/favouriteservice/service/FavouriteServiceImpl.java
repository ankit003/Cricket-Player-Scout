package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.repository.FavouriteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService{


    private final FavouriteRepository favouriteRepository;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }


    @Override
    public boolean addFavourite(Favourite favourite) {
        if(this.favouriteRepository.exists(Example.of(favourite))) {
            return false;
        }
        /*this.favouriteRepository.insert(favourite);
        if(!this.favouriteRepository.exists(Example.of(favourite))) {
            return false;
        }
        return true;*/
        try {
            this.favouriteRepository.insert(favourite);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean removeFavourite(String userName, long playerId) {
        if(!this.favouriteRepository.existsByUserNameAndPlayerId(userName, playerId)) {
            return false;
        }
        try {
            this.favouriteRepository.deleteByUserNameAndPlayerId(userName, playerId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Favourite> viewFavourites(String userName) {
        return this.favouriteRepository.findByUserName(userName);
    }
}

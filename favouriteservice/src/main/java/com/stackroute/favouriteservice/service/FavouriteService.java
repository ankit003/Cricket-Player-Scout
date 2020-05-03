package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.model.Favourite;

import java.util.List;

public interface FavouriteService {

    boolean addFavourite(Favourite favourite);

    boolean removeFavourite(String userName, long playerId);

    List<Favourite> viewFavourites(String userName);
}

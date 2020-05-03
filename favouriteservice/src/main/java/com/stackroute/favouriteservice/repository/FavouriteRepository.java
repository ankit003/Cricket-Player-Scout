package com.stackroute.favouriteservice.repository;

import java.util.List;

import com.stackroute.favouriteservice.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavouriteRepository extends MongoRepository<Favourite, String> {
    public List<Favourite> findByUserName(String userName);

    public void deleteByUserNameAndPlayerId(String userName, long playerId);

    public boolean existsByUserNameAndPlayerId(String userName, long playerId);
}

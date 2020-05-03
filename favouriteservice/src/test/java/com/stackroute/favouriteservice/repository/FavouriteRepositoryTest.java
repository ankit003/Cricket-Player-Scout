/*
package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.model.Favourite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouriteRepositoryTest {




    @Autowired
    private FavouriteRepository favouriteRepository;
    private Favourite favourite;

    @Before
    public void setUp() throws Exception {
        favourite = new Favourite();
        favourite.setUserName("abcde");
        favourite.setPlayerId(12345);
        favourite.setPlayerName("abcde");
    }

    @After
    public void tearDown() throws Exception {
        favouriteRepository.deleteAll();
    }

    @Test
    public void insertFavouriteTest() {
        favouriteRepository.insert(favourite);
        Favourite fetchedFavourite = favouriteRepository.findByUserName(favourite.getUserName()).get(0);
        Assert.assertEquals("abcde", fetchedFavourite.getUserName());
    }

    @Test
    public void existsFavouriteTest() {
        favouriteRepository.insert(favourite);
        boolean actual = favouriteRepository.exists(Example.of(favourite));
        Assert.assertEquals(true, actual);
    }


    @Test
    public void findByUserNameFavouriteTest() {
        favouriteRepository.insert(favourite);
        Favourite fetchedFavourite = favouriteRepository.findByUserName(favourite.getUserName()).get(0);
        Assert.assertEquals("abcde", fetchedFavourite.getUserName());
    }
}
*/

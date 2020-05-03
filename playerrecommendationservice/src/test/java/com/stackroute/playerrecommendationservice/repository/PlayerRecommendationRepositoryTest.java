/*
package com.stackroute.playerrecommendationservice.repository;

import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
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
public class PlayerRecommendationRepositoryTest {




    @Autowired
    private PlayerRecommendationRepository playerRecommendationRepository;
    private PlayerRecommendation playerRecommendation;

    @Before
    public void setUp() throws Exception {
        playerRecommendation = new PlayerRecommendation();
        playerRecommendation.setPlayerId((long) 12345);
        playerRecommendation.setPlayerName("abcde");
        playerRecommendation.setRecommendationCounter((long) 1);
    }

    @After
    public void tearDown() throws Exception {
        playerRecommendationRepository.deleteAll();
    }

    @Test
    public void insertPlayerRecommendationTest() {
        playerRecommendationRepository.insert(playerRecommendation);
        PlayerRecommendation fetchedPlayerRecommendation = playerRecommendationRepository.findByPlayerId(playerRecommendation.getPlayerId()).get(0);
        Assert.assertEquals(java.util.Optional.of((long) 12345).get(), fetchedPlayerRecommendation.getPlayerId());
    }

    @Test
    public void findByPlayerIdPlayerRecommendationTest() {
        playerRecommendationRepository.insert(playerRecommendation);
        PlayerRecommendation fetchedPlayerRecommendation = playerRecommendationRepository.findByPlayerId(playerRecommendation.getPlayerId()).get(0);
        Assert.assertEquals(java.util.Optional.of((long) 12345).get(), fetchedPlayerRecommendation.getPlayerId());
        playerRecommendationRepository.deleteAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteByPlayerIdPlayerRecommendationTest() {

        playerRecommendationRepository.insert(playerRecommendation);
        PlayerRecommendation fetchedPlayerRecommendation = playerRecommendationRepository.findByPlayerId(playerRecommendation.getPlayerId()).get(0);
        Assert.assertEquals(java.util.Optional.of((long) 12345).get(), fetchedPlayerRecommendation.getPlayerId());
        playerRecommendationRepository.deleteByPlayerId(fetchedPlayerRecommendation.getPlayerId());
        fetchedPlayerRecommendation = playerRecommendationRepository.findByPlayerId(playerRecommendation.getPlayerId()).get(0);

        playerRecommendationRepository.deleteAll();
    }
}
*/

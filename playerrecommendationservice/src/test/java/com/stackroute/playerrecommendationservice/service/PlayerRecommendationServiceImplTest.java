package com.stackroute.playerrecommendationservice.service;

import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
import com.stackroute.playerrecommendationservice.repository.PlayerRecommendationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerRecommendationServiceImplTest {

    private PlayerRecommendation playerRecommendation;
    @Mock
    private PlayerRecommendationRepository playerRecommendationRepository;
    @InjectMocks
    private PlayerRecommendationServiceImpl playerRecommendationServiceImpl;
    private List<PlayerRecommendation> playerRecommendationList = null;
    Optional<PlayerRecommendation> options;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        playerRecommendation = new PlayerRecommendation();

        playerRecommendation.setPlayerId((long) 12345);
        playerRecommendation.setPlayerName("abcde");
        playerRecommendation.setRecommendationCounter((long) 1);

        playerRecommendationList = new ArrayList<>();
        playerRecommendationList.add(playerRecommendation);
        options = Optional.of(playerRecommendation);
    }



    @Test
    public void addPlayerRecommendationSuccess() throws Exception {
        when(playerRecommendationRepository.findByPlayerId(any())).thenReturn(playerRecommendationList);
        when(playerRecommendationRepository.insert((PlayerRecommendation) any())).thenReturn(playerRecommendation);
        boolean actual = playerRecommendationServiceImpl.addPlayerRecommendation(playerRecommendation);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void addPlayerRecommendationFailure() throws Exception {
        when(playerRecommendationRepository.findByPlayerId(any())).thenReturn(new ArrayList<>());
        when(playerRecommendationRepository.insert((PlayerRecommendation) any())).thenReturn(playerRecommendation);
        boolean actual = playerRecommendationServiceImpl.addPlayerRecommendation(playerRecommendation);
        Assert.assertEquals(false, actual);
    }


    @Test
    public void viewPlayerRecommendationsSuccess() throws Exception {
        when(playerRecommendationRepository.findAll((Sort) any())).thenReturn(playerRecommendationList);
        List<PlayerRecommendation> actual = playerRecommendationServiceImpl.viewPlayerRecommendations();
        Assert.assertEquals(playerRecommendationList, actual);
    }
}

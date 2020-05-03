package com.stackroute.playerrecommendationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.playerrecommendationservice.model.Message;
import com.stackroute.playerrecommendationservice.model.PlayerRecommendation;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PlayerRecommendationControllerTest {




    @Autowired
    private MockMvc mockMvc;
    private PlayerRecommendation playerRecommendation;
    @MockBean
    private PlayerRecommendationService playerRecommendationService;
    @InjectMocks
    private PlayerRecommendationController playerRecommendationController;
    private List<PlayerRecommendation> playerRecommendationList = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerRecommendationController).build();
        playerRecommendation = new PlayerRecommendation();

        playerRecommendation.setPlayerId((long) 12345);
        playerRecommendation.setPlayerName("abcde");
        playerRecommendation.setRecommendationCounter((long) 1);

        playerRecommendationList = new ArrayList<>();
        playerRecommendationList.add(playerRecommendation);
    }

    @Test
    public void addPlayerRecommendationApiSuccess() throws Exception {
        when(playerRecommendationService.addPlayerRecommendation(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/playerRecommendation/addPlayerRecommendation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(playerRecommendation)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addPlayerRecommendationSuccess() throws Exception {
        Message message = new Message((long) 1, "abc", (long) 1);
        when(playerRecommendationService.addPlayerRecommendation(any())).thenReturn(true);
    }

    @Test
    public void addPlayerRecommendationApiFailure() throws Exception {
        when(playerRecommendationService.addPlayerRecommendation(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/playerRecommendation/addPlayerRecommendation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(playerRecommendation)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addPlayerRecommendationException() throws Exception {
        when(playerRecommendationService.addPlayerRecommendation(any())).thenThrow(ArrayIndexOutOfBoundsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/playerRecommendation/addPlayerRecommendation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(playerRecommendation)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void viewPlayerRecommendationsSuccess() throws Exception {
        when(playerRecommendationService.viewPlayerRecommendations()).thenReturn(playerRecommendationList);
        mockMvc.perform(MockMvcRequestBuilders.get("/playerRecommendation/viewPlayerRecommendations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void viewPlayerRecommendationsFailure() throws Exception {
        when(playerRecommendationService.viewPlayerRecommendations()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/playerRecommendation/viewPlayerRecommendations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void viewPlayerRecommendationsException() throws Exception {
        when(playerRecommendationService.viewPlayerRecommendations()).thenThrow(ArrayIndexOutOfBoundsException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/playerRecommendation/viewPlayerRecommendations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

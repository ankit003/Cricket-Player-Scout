package com.stackroute.favouriteservice.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.model.Message;
import com.stackroute.favouriteservice.service.FavouriteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavouriteControllerTest {




    @Autowired
    private MockMvc mockMvc;
    private Favourite favourite;
    @MockBean
    private FavouriteService favouriteService;
    @MockBean
    private Source source;
    @InjectMocks
    private FavouriteController favouriteController;
    private List<Favourite> favouriteList = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();
        favourite = new Favourite();

        favourite.setPlayerId(12345);
        favourite.setPlayerName("abcde");
        favourite.setUserName("uvwxyz");

        favouriteList = new ArrayList<>();
        favouriteList.add(favourite);
    }

    @Test
    public void removeFavouriteSuccess() throws Exception {
        when(favouriteService.removeFavourite(anyString(), anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/favourite/removeFavourite/abc/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addFavouriteFailure() throws Exception {
        when(favouriteService.addFavourite(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/favourite/addToFavourite")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void removeFavouriteFailure() throws Exception {
        when(favouriteService.removeFavourite(anyString(), anyLong())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/favourite/removeFavourite/abc/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addFavouriteException() throws Exception {
        when(favouriteService.addFavourite(any())).thenThrow(ArrayIndexOutOfBoundsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/favourite/addToFavourite")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void removeFavouriteException() throws Exception {
        when(favouriteService.removeFavourite(anyString(), anyLong())).thenThrow(ArrayIndexOutOfBoundsException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/favourite/removeFavourite/abc/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void viewFavouritesSuccess() throws Exception {
        when(favouriteService.viewFavourites(any())).thenReturn(favouriteList);
        mockMvc.perform(MockMvcRequestBuilders.get("/favourite/viewFavourites/abcde")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void viewFavouritesFailure() throws Exception {
        when(favouriteService.viewFavourites(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/favourite/viewFavourites/abcde")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void viewFavouritesException() throws Exception {
        when(favouriteService.viewFavourites(any())).thenThrow(ArrayIndexOutOfBoundsException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/favourite/viewFavourites/abcde")
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

package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.repository.FavouriteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FavouriteServiceImplTest {

    private Favourite favourite;
    @Mock
    private FavouriteRepository favouriteRepository;
    @InjectMocks
    private FavouriteServiceImpl favouriteServiceImpl;
    private List<Favourite> favouriteList = null;
    Optional<Favourite> options;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        favourite = new Favourite();

        favourite.setUserName("abcde");
        favourite.setPlayerName("abcde");
        favourite.setPlayerId(12345);

        favouriteList = new ArrayList<>();
        favouriteList.add(favourite);
        options = Optional.of(favourite);
    }

    @Test
    public void addFavouriteSuccess() throws Exception {
        when(favouriteRepository.exists(any())).thenReturn(false);
        when(favouriteRepository.insert((Favourite) any())).thenReturn(favourite);
        boolean actual = favouriteServiceImpl.addFavourite(favourite);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void removeFavouriteSuccess() throws Exception {
        when(favouriteRepository.existsByUserNameAndPlayerId(anyString(),anyLong())).thenReturn(true);
        doNothing().when(favouriteRepository).deleteByUserNameAndPlayerId(anyString(),anyLong());
        boolean actual = favouriteServiceImpl.removeFavourite(favourite.getUserName(),favourite.getPlayerId());
        Assert.assertEquals(true, actual);
    }


    @Test
    public void addFavouriteFailure() throws Exception {
        when(favouriteRepository.exists(any())).thenReturn(true);
        when(favouriteRepository.insert((Favourite) any())).thenReturn(favourite);
        boolean actual = favouriteServiceImpl.addFavourite(favourite);
        Assert.assertEquals(false, actual);
    }

    @Test
    public void removeFavouriteFailure() throws Exception {
        when(favouriteRepository.existsByUserNameAndPlayerId(anyString(),anyLong())).thenReturn(false);
        doNothing().when(favouriteRepository).deleteByUserNameAndPlayerId(anyString(),anyLong());
        boolean actual = favouriteServiceImpl.removeFavourite(favourite.getUserName(),favourite.getPlayerId());
        Assert.assertEquals(false, actual);
    }


    @Test
    public void addFavouriteException() throws Exception {
        when(favouriteRepository.exists(any())).thenReturn(false);
        when(favouriteRepository.insert((Favourite) any())).thenThrow(ArrayIndexOutOfBoundsException.class);
        boolean actual = favouriteServiceImpl.addFavourite(favourite);
        Assert.assertEquals(false, actual);
    }

    @Test
    public void removeFavouriteException() throws Exception {
        when(favouriteRepository.existsByUserNameAndPlayerId(anyString(),anyLong())).thenReturn(false);
        doThrow(ArrayIndexOutOfBoundsException.class).when(favouriteRepository).deleteByUserNameAndPlayerId(anyString(),anyLong());
        boolean actual = favouriteServiceImpl.removeFavourite(favourite.getUserName(),favourite.getPlayerId());
        Assert.assertEquals(false, actual);
    }

    @Test
    public void viewFavouritesTest() throws Exception {
        when(favouriteRepository.findByUserName(any())).thenReturn(favouriteList);
        List<Favourite> actual = favouriteServiceImpl.viewFavourites("vwxyz");
        Assert.assertEquals(favouriteList, actual);
    }
}

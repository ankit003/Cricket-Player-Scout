package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private User user;
    @InjectMocks
    private UserServiceImpl userService;

    Optional<User> optional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setUserName("Admin");
        user.setPassword("123456");
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() {

        Mockito.when(userRepository.saveAndFlush((User) any())).thenReturn(user);
        boolean flag = userService.saveUser(user);
        Assert.assertEquals(true, flag);

    }


    @Test
    public void testSaveUserFailure() {

        Mockito.when(userRepository.saveAndFlush((User) any())).thenReturn(null);
        boolean flag = userService.saveUser(user);
        Assert.assertEquals(false, flag);

    }

    @Test
    public void testFindByUserIdAndPassword() {
        Mockito.when(userRepository.findByUserNameAndPassword(anyString(), anyString())).thenReturn(user);
        User fetchedUser = userService.findByUserNameAndPassword("Admin", "123456");
        Assert.assertEquals("Admin", fetchedUser.getUserName());
    }

    @Test
    public void testFindByUserIdAndPasswordFailure() {
        Mockito.when(userRepository.findByUserNameAndPassword(anyString(), anyString())).thenReturn(null);
        User fetchedUser = userService.findByUserNameAndPassword("Admin", "123456");
        Assert.assertNull(fetchedUser);
    }


    @Test
    public void testExistsUserSuccess() {
        Mockito.when(userRepository.existsById(anyString())).thenReturn(true);
        boolean fetchedUser = userService.existsUser(user);
        Assert.assertEquals(true,fetchedUser);
    }

    @Test
    public void testExistsUserFailure() {
        Mockito.when(userRepository.existsById(anyString())).thenReturn(false);
        boolean fetchedUser = userService.existsUser(user);
        Assert.assertEquals(false,fetchedUser);
    }
}

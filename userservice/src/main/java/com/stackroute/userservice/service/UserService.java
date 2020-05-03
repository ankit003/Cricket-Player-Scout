package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;

public interface UserService {

    public User findByUserNameAndPassword(String userName, String password);

    boolean saveUser(User user);

    boolean existsUser(User user);
}

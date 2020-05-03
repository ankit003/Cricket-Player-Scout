package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUserNameAndPassword(String userName, String password) {

        User currentUser = this.userRepository.findByUserNameAndPassword(userName, password);
        if (currentUser != null) {
            return currentUser;
        } else {
            return null;
        }
    }

    @Override
    public boolean saveUser(User user) {
        User returnObject = (User) this.userRepository.saveAndFlush(user);
        if (returnObject != null) {
            return true;
        } else {
            //throw new Exception("User was already found");
            return false;
        }
    }

    @Override
    public boolean existsUser(User user) {
        List<User> users = new ArrayList<>();
        if(this.userRepository.existsById(user.getUserName())) {
            return true;
        }
        return false;
    }
}

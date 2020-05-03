package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }


    /*@PostMapping(value = "/api/v1/auth/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        if(this.userService.existsUser(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            this.userService.saveUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            // e.printStackTrace();
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }*/

    @PostMapping(value = "/api/v1/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if(this.userService.existsUser(user)) {
            Map<String, String> response = new HashMap<>();
            response.put("error","User with same user name already exists");
            return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
        }
        if(this.userService.saveUser(user)) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> getUser(@RequestBody User user) {
        User returnuser = null;
        returnuser = this.userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (returnuser != null) {

            String token;
            try {
                token = this.getToken(user.getUserName(), user.getPassword());
                Map<String, String> loggedInUser = new HashMap<>();
                loggedInUser.put("userName",returnuser.getUserName());
                loggedInUser.put("firstName",returnuser.getFirstName());
                loggedInUser.put("token",token);
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> getUser(@RequestBody User user) {
        User returnuser = null;
        try {

            returnuser = this.userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if (returnuser != null) {

                String token;
                try {
                    token = this.getToken(user.getUserName(), user.getPassword());
                    Map<String, String> loggedInUser = new HashMap<>();
                    loggedInUser.put("userName",returnuser.getUserName());
                    loggedInUser.put("firstName",returnuser.getFirstName());
                    loggedInUser.put("token",token);
                    return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
                }

            } else {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }*/

    // Generate JWT token
    //dont pass password
    public String getToken(String username, String password) throws Exception {
        String SECRET = "SecretKeyToGenJWTs";
        long EXPIRATION_TIME = 864_000_000; // 10 days
        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("UserId", username + "");
//        claims.put("password", password);

        //SECRET should be equal to secret in JWTFilter

        return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

    }
}

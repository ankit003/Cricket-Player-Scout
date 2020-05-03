package com.stackroute.userservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class "User" will be acting as the data model for the User Table in the database.
 * Please note that this class is annotated with @Entity annotation.
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation.
 * If it finds any, then it will begin the process of looking through that particular
 * Java object to recreate it as a table in your database.
 */
@Entity
//@Table(name = "User")
public class User {


    @Id
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

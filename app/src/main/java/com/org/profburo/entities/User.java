package com.org.profburo.entities;

import com.org.profburo.network.responsesEntities.login.LoginResponse;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private String email;
    private String password;
    private String permission;
    private String serialPassport;
    private String numberPassport;
    private String dateJoined;
    private String phone;

    public User(Integer id, String username, String firstName, String lastName, String middleName, String birthday, String email, String password, String permission, String serialPassport, String numberPassport, String phone) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.serialPassport = serialPassport;
        this.numberPassport = numberPassport;
        this.phone = phone;
    }

    public User(Integer id, String username, String firstName, String lastName, String email, String password, String permission, String serialPassport, String numberPassport, String dateJoined) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.serialPassport = serialPassport;
        this.numberPassport = numberPassport;
        this.dateJoined = dateJoined;
    }

    public User(Integer id, String username, String firstName, String lastName, String email, String password, String permission, String serialPassport, String numberPassport) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.serialPassport = serialPassport;
        this.numberPassport = numberPassport;
    }

    public User(LoginResponse response)
    {
         this(response.getId(), response.getUsername(), response.getFirstName(), response.getLastName(), response.getMiddleName(), response.getBirthday(), response.getEmail(), response.getPassword(), response.getPermission(), response.getSerialPassport(), response.getNumberPassport(), response.getPhone());
    }

    public User() {
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSerialPassport() {
        return serialPassport;
    }

    public void setSerialPassport(String serialPassport) {
        this.serialPassport = serialPassport;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

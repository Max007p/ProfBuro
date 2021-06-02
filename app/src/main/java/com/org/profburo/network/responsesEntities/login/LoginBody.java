package com.org.profburo.network.responsesEntities.login;

public class LoginBody {
    private String phoneOrEmail;
    private String password;

    public LoginBody(String phoneOrEmail, String password) {
        this.phoneOrEmail = phoneOrEmail;
        this.password = password;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

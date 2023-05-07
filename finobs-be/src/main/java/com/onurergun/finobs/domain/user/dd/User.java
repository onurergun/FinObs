package com.onurergun.finobs.domain.user.dd;

import java.util.Date;

public class User {
    private Firstname firstname;
    private Lastname lastname;
    private Username username;
    private Email email;
    private Password password;
    private Date registerDate;

    private User() {
        registerDate = new Date();
    }

    public User(Firstname firstname, Lastname lastname, Username username, Email email, Password password) {
        this();

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Firstname getFirstname() {
        return firstname;
    }

    public Lastname getLastname() {
        return lastname;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Password getPassword() {
        return password;
    }
}

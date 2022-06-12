package com.onurergun.finobsbe.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = User.UNIQUE_CONSTRAINT_USERNAME, columnNames = {"userName"}),
        @UniqueConstraint(name = User.UNIQUE_CONSTRAINT_EMAIL, columnNames = {"email"})})
public class User {

    public static final String UNIQUE_CONSTRAINT_USERNAME = "unique_constraint_username";
    public static final String UNIQUE_CONSTRAINT_EMAIL = "unique_constraint_email";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date registerDate;

    public User()
    {
        registerDate = new Date();
    }
}

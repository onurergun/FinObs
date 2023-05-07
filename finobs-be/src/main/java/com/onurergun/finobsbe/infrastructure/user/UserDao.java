package com.onurergun.finobsbe.infrastructure.user;

import com.onurergun.finobsbe.domain.user.dd.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = UserDao.UNIQUE_CONSTRAINT_USERNAME, columnNames = {"userName"}),
        @UniqueConstraint(name = UserDao.UNIQUE_CONSTRAINT_EMAIL, columnNames = {"email"})})
@AllArgsConstructor
@Getter
@Setter
public class UserDao {

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

    public UserDao()
    {
        registerDate = new Date();
    }

    public User to() {
        return new User(
                new Firstname(getFirstName()),
                new Lastname(getLastName()),
                new Username(getUserName()),
                new Email(getEmail()),
                new Password(getPassword())
        );
    }

    public static UserDao from(User u) {
        return new UserDao(
                null,
                u.getFirstname().value(),
                u.getLastname().value(),
                u.getUsername().value(),
                u.getEmail().value(),
                u.getPassword().value(),
                u.getRegisterDate()
        );
    }

}

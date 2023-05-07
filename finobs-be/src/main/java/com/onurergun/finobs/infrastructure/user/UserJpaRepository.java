package com.onurergun.finobs.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserDao, Long> {

    Optional<UserDao> findUserDaoByUserName(String username);

    Optional<UserDao> findUserDaoByUserNameIgnoreCase(String username);

}

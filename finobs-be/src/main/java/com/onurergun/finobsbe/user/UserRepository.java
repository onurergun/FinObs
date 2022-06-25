package com.onurergun.finobsbe.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserNameIgnoreCase(String username);

}

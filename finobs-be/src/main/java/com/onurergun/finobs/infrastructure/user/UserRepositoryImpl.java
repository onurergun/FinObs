package com.onurergun.finobs.infrastructure.user;

import com.onurergun.finobs.domain.user.UserRepository;
import com.onurergun.finobs.domain.user.dd.User;
import com.onurergun.finobs.domain.user.dd.Username;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {

        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void createOrUpdate(User u) {
        userJpaRepository.save(UserDao.from(u));
    }

    @Override
    public Optional<User> findUserByUsername(Username username) {
        return userJpaRepository.findUserDaoByUserName(username.value()).map(
                userDao -> userDao.to()
        );
    }

    @Override
    public Optional<User> findUserByUsernameIgnoreCase(Username username) {
        return userJpaRepository.findUserDaoByUserNameIgnoreCase(username.value()).map(
                userDao -> userDao.to()
        );
    }

    @Override
    public List<User> getAll() {
        return userJpaRepository.findAll().stream().map(
                userDao -> userDao.to()
        ).toList();
    }
}


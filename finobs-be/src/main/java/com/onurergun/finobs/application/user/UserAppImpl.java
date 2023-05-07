package com.onurergun.finobs.application.user;

import com.onurergun.finobs.domain.user.UserManager;
import com.onurergun.finobs.domain.user.dd.*;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UserAppImpl implements UserApp {

    private final UserManager userManager;

    private final PasswordEncoder passwordEncoder;

    public UserAppImpl(UserManager userManager) {
        this.userManager = userManager;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.encodePassword(passwordEncoder);
        User user = userDto.to();
        userManager.createUser(user);
        return UserDto.from(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userManager.getAllUsers().stream()
                .map(UserDto::from)
                .toList();
    }

    @Override
    public UserDto getByUsername(String username) {
        Username uName = new Username(username);
        Optional<User> userByUsernameIgnoreCaseOptional = userManager.findUserByUsernameIgnoreCase(uName);
        if (userByUsernameIgnoreCaseOptional.isEmpty()) return null;
        return UserDto.from(userByUsernameIgnoreCaseOptional.get());
    }


}

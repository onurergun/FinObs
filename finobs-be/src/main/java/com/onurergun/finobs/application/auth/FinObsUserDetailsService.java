package com.onurergun.finobs.application.auth;

import com.onurergun.finobs.application.user.UserApp;
import com.onurergun.finobs.application.user.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FinObsUserDetailsService implements UserDetailsService {

    private final UserApp userApp;

    public FinObsUserDetailsService(UserApp userApp) {
        this.userApp = userApp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userApp.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new AuthUserDetails(user);
    }
}

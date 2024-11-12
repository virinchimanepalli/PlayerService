package com.intuit.playerservice.service;

import com.intuit.playerservice.constant.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${local.userId}")
    private String localUserId;

    @Value("${local.password}")
    private String localPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with fetching user details from a database if needed
        if (localUserId.equals(username)) {
            return new User(localUserId, localPassword, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND);
        }
    }
}



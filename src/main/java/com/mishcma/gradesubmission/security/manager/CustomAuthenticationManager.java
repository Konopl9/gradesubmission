package com.mishcma.gradesubmission.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mishcma.gradesubmission.entity.User;
import com.mishcma.gradesubmission.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserService userService;
    private BCryptPasswordEncoder bvBCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser(authentication.getName());
        if (!bvBCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }

}

package com.ranji.labourlink.Security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserLoginRepo userRepo;

    public CustomUserDetailsService(UserLoginRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber)
            throws UsernameNotFoundException {

        User user = userRepo.findByphno(phoneNumber);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getPhoneNumber())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
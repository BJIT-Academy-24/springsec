package com.example.springsec.service;

import com.example.springsec.UserPrincipal;
import com.example.springsec.model.User;
import com.example.springsec.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    Logger logger = Logger.getLogger(MyUserDetailsService.class.getName());

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        logger.info("User Found: "+userRepo.findByUsername(username));
        return new UserPrincipal(user);
    }
}

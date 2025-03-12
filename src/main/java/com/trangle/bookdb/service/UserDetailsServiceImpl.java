package com.trangle.bookdb.service;

import java.util.Optional;

import com.trangle.bookdb.domain.AppUser;
import com.trangle.bookdb.domain.AppUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder; 
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //UserDetailsService is a Spring interface used for user authetication and authorization 
    private final AppUserRepository userRepository; 

    public UserDetailsServiceImpl(AppUserRepository userRepository){
        this.userRepository = userRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUserName(userName);
        UserBuilder builder = null; 

        if (user.isPresent()){
            AppUser currentUser = user.get(); 
            builder = org.springframework.security.core.userdetails.User.withUsername(userName); 
            builder.password(currentUser.getPassword()); 
            builder.roles(currentUser.getRole()); 
        } else {
            throw new UsernameNotFoundException("User not found"); 
        }

        return builder.build(); 

    }

}

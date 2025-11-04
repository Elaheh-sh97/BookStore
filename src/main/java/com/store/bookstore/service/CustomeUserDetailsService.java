package com.store.bookstore.service;

import com.store.bookstore.model.Users;
import com.store.bookstore.repository.UserRepository;
import com.store.bookstore.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomeUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  Users users=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email + " not found"));

        return new CustomUserDetails(users);
    }


}

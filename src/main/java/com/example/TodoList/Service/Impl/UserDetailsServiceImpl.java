package com.example.TodoList.Service.Impl;

import com.example.TodoList.Model.UserEntity;
import com.example.TodoList.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found. Please check your credentials or register a new account."));

        Collection<? extends GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                authorities
        );
    }
}

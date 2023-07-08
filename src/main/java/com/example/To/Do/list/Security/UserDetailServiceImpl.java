package com.example.To.Do.list.Security;

import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Model.User;
import com.example.To.Do.list.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(()-> {throw new ResourceNotFoundException("No se ha encontrado el usuario ingresado.");
                });
        return new UserDetailImpl(user);
    }
}

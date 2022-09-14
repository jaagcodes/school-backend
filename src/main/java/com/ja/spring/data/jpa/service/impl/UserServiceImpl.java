package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.entity.User;
import com.ja.spring.data.jpa.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepo repo;

    //Definición de cómo Spring Security sabe donde está al información sobre Usuarios-Roles
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOneByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Usuario no existe", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        user.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, roles);
        return userDetails;
    }
}

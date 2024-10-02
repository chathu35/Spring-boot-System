package com.example.possystem.service.impl;


import com.example.possystem.dto.impl.UserDTO;
import com.example.possystem.entitiy.User;
import com.example.possystem.repository.UserRepository;
import com.example.possystem.service.UserService;
import com.example.possystem.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())){
            return VarList.Not_Acceptable;
        }else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userDTO.setRole("DASH_ADMIN");
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.Created;
        }
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)){
            User user = userRepository.findByEmail(String.valueOf(username));
            return modelMapper.map(user,UserDTO.class);
        }else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthority(user));
    }


    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }


}

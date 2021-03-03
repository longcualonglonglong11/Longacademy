package com.kita.service.impl;

import com.kita.entity.User;
import com.kita.entity.dto.AuthDto;
import com.kita.repository.RoleRepository;
import com.kita.repository.UserRepository;
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
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Email la :" + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email not exists");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        String roleName = user.getRole().getName();
        String roleName = roleRepository.findById(user.get_roleId()).get().getName();
        authorities.add(new SimpleGrantedAuthority(roleName));


        return new AuthDto(user.getEmail(), user.getPassword(), authorities, user.get_id().toHexString(), user.getFullname(),
                user.getAvatar(), user.getBalance(), user.get_roleId().toHexString());

    }
}

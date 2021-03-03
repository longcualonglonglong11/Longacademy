package com.kita.entity.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthDto extends User implements UserDetails {
    private String email;
    private String id;
    private String fullname;
    private String avatar;
    private String password;
    private double balance;
    String roleId;
    String roleName;

    public AuthDto() {
        super(" ", " ", new ArrayList<>());
    }

    public AuthDto(String email, String password, Collection<? extends GrantedAuthority> authorities, String id, String fullname, String avatar, double balance, String roleId) {
        super(email, password, authorities);
        this.id = id;
        this.fullname = fullname;
        this.avatar = avatar;
        this.password = password;
        this.roleId = roleId;
        this.balance = balance;
    }

    public AuthDto(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public AuthDto(String email, String password, SimpleGrantedAuthority authority) {

        super(email, password, Collections.singleton(authority));
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

package com.kita.service.impl;

import com.kita.entity.dto.UserDto;
import com.kita.service.UserService;
import org.bson.types.ObjectId;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findById(ObjectId id) {
        return null;
    }

    @Override
    public boolean add(UserDto entity) {
        return false;
    }

    @Override
    public boolean update(UserDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(ObjectId id) {
        return false;
    }
}

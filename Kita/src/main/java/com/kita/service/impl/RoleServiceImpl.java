package com.kita.service.impl;

import com.kita.entity.dto.RoleDto;
import com.kita.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<RoleDto> findAll() {
        return null;
    }

    @Override
    public RoleDto findById(String id) {
        return null;
    }

    @Override
    public boolean add(RoleDto entity) {
        return false;
    }

    @Override
    public boolean update(RoleDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}

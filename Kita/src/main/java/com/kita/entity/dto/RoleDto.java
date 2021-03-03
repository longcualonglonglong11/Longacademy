package com.kita.entity.dto;

import com.kita.entity.Role;

public class RoleDto extends Role {
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.kita.entity.dto;

import com.kita.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public class UserDto extends User {
    private String idStr;
    private String token;
    private String roleId;
    List<CourseDto> courseInCart;
    List<CourseDto> buyedCourse;
    RoleDto roleDto;

    public UserDto(ObjectId _id, String email, String fullname, String password, String avatar, String phone, double balance, ObjectId _roleId) {
        super(_id, email, fullname, password, avatar, phone, balance, _roleId);
        this.idStr = _id.toHexString();
    }

    public UserDto(User user, String token, List<CourseDto> courseInCart, List<CourseDto> buyedCourse) {
        super(user.get_id(), user.getEmail(), user.getFullname(), user.getPassword(), user.getAvatar(), user.getPhone(), user.getBalance(), user.get_roleId());
        this.idStr = user.get_id().toHexString();
        this.roleId = user.get_roleId().toHexString();
        this.token = token;
        this.courseInCart = courseInCart;
        this.buyedCourse = buyedCourse;

    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<CourseDto> getCourseInCart() {
        return courseInCart;
    }

    public void setCourseInCart(List<CourseDto> courseInCart) {
        this.courseInCart = courseInCart;
    }

    public List<CourseDto> getBuyedCourse() {
        return buyedCourse;
    }

    public void setBuyedCourse(List<CourseDto> buyedCourse) {
        this.buyedCourse = buyedCourse;
    }
}

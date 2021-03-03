package com.kita.entity;

import com.kita.utility.constant.ObjectConstant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = ObjectConstant.USER_LIST)
public class User {
    @Id
    private ObjectId _id;
    private String email;
    private String fullname;
    private String password;
    private String avatar;
    private String phone;
    private double balance;
    @Field("role_id")
    private ObjectId _roleId;
//    public User(){}
    public User(ObjectId _id, String email, String fullname, String password, String avatar, String phone, double balance, ObjectId _roleId) {
        this._id = _id;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.balance = balance;
        this._roleId = _roleId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ObjectId get_roleId() {
        return _roleId;
    }

    public void set_roleId(ObjectId _roleId) {
        this._roleId = _roleId;
    }
}

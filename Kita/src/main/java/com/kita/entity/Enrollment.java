package com.kita.entity;

import com.kita.utility.constant.ObjectConstant;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = ObjectConstant.ENROLLMENT_LIST)
@CompoundIndex(name = "compound_id_courses_users_index", def = "{'course_id': 1, 'user_id': 1}")
public class Enrollment {
    @Id
    private ObjectId _id;
    @Field(name = "user_id")
    private ObjectId _userId;
    @Field(name = "course_id")
    private ObjectId _courseId;
    @Field(name = "last_enrolled")
    private Date lastEnrolled;
    @Field(name = "is_in_cart")
    private boolean isInCart;
    @Field(name = "is_buy")
    private boolean isBuy;

    public Enrollment(Date lastEnrolled, boolean isInCart, boolean isBuy) {
        this.lastEnrolled = lastEnrolled;
        this.isInCart = isInCart;
        this.isBuy = isBuy;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId get_userId() {
        return _userId;
    }

    public void set_userId(ObjectId _userId) {
        this._userId = _userId;
    }

    public ObjectId get_courseId() {
        return _courseId;
    }

    public void set_courseId(ObjectId _courseId) {
        this._courseId = _courseId;
    }

    public Date getLastEnrolled() {
        return lastEnrolled;
    }

    public void setLastEnrolled(Date lastEnrolled) {
        this.lastEnrolled = lastEnrolled;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }


}

package com.kita.entity;

import com.kita.utility.constant.ObjectConstant;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = ObjectConstant.TARGET_LIST)
public class Target {
    @Id
    private ObjectId _id;
    private String title;
    @Indexed(name = "course_id_index")
    @Field("course_id")
    private ObjectId _courseId;

    public Target(String title) {
        this.title = title;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId get_courseId() {
        return _courseId;
    }

    public void set_courseId(ObjectId _courseId) {
        this._courseId = _courseId;
    }
}

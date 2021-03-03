package com.kita.entity;

import com.kita.utility.constant.ObjectConstant;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = ObjectConstant.VIDEO_LIST)
public class Video {
    @Id
    private ObjectId _id;
    private String title;
    @Indexed(name = "course_id_index")
    @Field("course_id")
    private ObjectId _courseId;

    private String tag;
    private String url;
    private int length;
    @Field("time_count")
    private int timeCount;

    public Video(String title, String tag, int length, int timeCount, String url) {
        this.title = title;
        this.tag = tag;
        this.length = length;
        this.timeCount = timeCount;
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
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

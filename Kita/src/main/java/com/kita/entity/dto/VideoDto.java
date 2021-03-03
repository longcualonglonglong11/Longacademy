package com.kita.entity.dto;

import com.kita.entity.Video;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class VideoDto extends Video {
    private String id;
    private String courseId;

    public VideoDto(Video video) {
        super(video.getTitle(), video.getTag(), video.getLength(), video.getTimeCount(), video.getUrl());
        this.id = video.get_id().toHexString();
        this.courseId = video.get_courseId().toHexString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

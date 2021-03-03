package com.kita.entity.dto;

import com.kita.entity.Target;

public class TargetDto extends Target {
    private String id;
    private String courseId;

    public TargetDto(Target target) {
        super(target.getTitle());
        this.id = target.get_id().toHexString();
        this.courseId = target.get_courseId().toHexString();
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

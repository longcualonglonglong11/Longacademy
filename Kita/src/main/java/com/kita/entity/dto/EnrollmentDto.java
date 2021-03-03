package com.kita.entity.dto;

import com.kita.entity.Enrollment;

import java.util.Date;

public class EnrollmentDto extends Enrollment {
    private String courseId;
    private String userId;
    public EnrollmentDto(Enrollment enrollment) {
        super(enrollment.getLastEnrolled(), enrollment.isInCart(), enrollment.isBuy());
        this.courseId = enrollment.get_courseId().toHexString();
        this.userId =  enrollment.get_userId().toHexString();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

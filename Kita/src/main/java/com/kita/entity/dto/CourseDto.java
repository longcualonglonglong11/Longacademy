package com.kita.entity.dto;

import com.kita.entity.Course;
import com.kita.entity.Target;
import com.kita.entity.Video;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDto extends Course {
    private String id;
    private String categoryId;
    private List<TargetDto> targetDtos;
    private List<VideoDto> videoDtos;
    private CategoryDto categoryDto;

    //    public CourseDto(String id, String categoryId, String title, String image, int lecturerCount, int lengthVideos, int viewCount, double price, int discount, String description, String content, Date lastUpdate, String author, int students) {
//        super(title,  image,  lecturerCount,  lengthVideos,  viewCount,  price,  discount,  description,  content,  lastUpdate,  author);
//        this.id = id;
//        this.categoryId = categoryId;
//        this.students = students;
//    }
    public CourseDto(Course course) {
        super(course.getTitle(), course.getImage(), course.getLecturerCount(), course.getLengthVideos(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getDescription(), course.getContent(), course.getLastUpdate(), course.getAuthor(), course.getStudents(), course.getCategory());
        this.id = course.get_id().toHexString();
        if (course.getCategory() != null)
            this.categoryDto = new CategoryDto(course.getCategory());
        if (course.getVideos() != null) {
            List<VideoDto> videoDtos = new ArrayList<>();

            for (Video video :
                    course.getVideos()) {
                videoDtos.add(new VideoDto(video));
            }
            this.videoDtos = videoDtos;
        }
        if (course.getTargets() != null) {
            List<TargetDto> targetDtos = new ArrayList<>();

            for (Target target :
                    course.getTargets()) {
                targetDtos.add(new TargetDto(target));
            }
            this.targetDtos = targetDtos;
        }


    }

    public List<VideoDto> getVideoDtos() {
        return videoDtos;
    }

    public void setVideoDtos(List<VideoDto> videoDtos) {
        this.videoDtos = videoDtos;
    }

    public List<TargetDto> getTargetDtos() {
        return targetDtos;
    }

    public void setTargetDtos(List<TargetDto> targetDtos) {
        this.targetDtos = targetDtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}

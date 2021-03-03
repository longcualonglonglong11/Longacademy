package com.kita.entity.dto;


import com.kita.entity.Category;
import com.kita.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends Category {

    private List<CourseDto> courseDtos;
    private String id;
    private int students;

    public CategoryDto() {
        super();

    }


    public CategoryDto(String id, String title, String icon, List<CourseDto> courses) {
        super(title, icon);
        this.id = id;
    }

    public CategoryDto(Category category) {
        super(category.getTitle(), category.getIcon());
        this.id = category.get_id().toString();
        List<CourseDto> courseDtos = new ArrayList<>();
        if (category.getCourses() != null)
            for (Course course :
                    category.getCourses()) {
                course.getCategory().setCourses(null);

                CourseDto courseDto = new CourseDto(course);
                courseDtos.add(courseDto);
            }
        this.courseDtos = courseDtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CourseDto> getCourseDtos() {
        return courseDtos;
    }

    public void setCourseDtos(List<CourseDto> courseDtos) {
        this.courseDtos = courseDtos;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "courseDtos=" + courseDtos +
                ", id='" + id + '\'' +
                '}';
    }
}

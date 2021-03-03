package com.kita.service;

import com.kita.entity.Category;
import com.kita.entity.Course;
import com.kita.entity.dto.CategoryDto;
import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.EnrollmentDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService extends GenericService<CourseDto, String> {
    List<CourseDto> findAll(Pageable pageable);

    List<CourseDto> findTopCourseSeller(int numOfTop);

    List<CourseDto> findTopPopularCourses(int numOfTop);

    List<CourseDto> findTopDiscountCourses(int numOfTop);

}

package com.kita.service;

import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.EnrollmentDto;
import com.kita.service.GenericService;
import org.bson.types.ObjectId;

import java.util.List;

public interface EnrollmentService extends GenericService<EnrollmentDto, String> {
     List<CourseDto> findAllCourseInCartByUserId(ObjectId userId);
     List<CourseDto> findAllBuyedCourseByUserId(ObjectId userId);

}

package com.kita.service.impl;

import com.kita.entity.Course;
import com.kita.entity.Video;
import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.EnrollmentDto;
import com.kita.repository.*;
import com.kita.service.EnrollmentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TargetRepository targetRepository;
    @Autowired
    VideoRepository videoRepository;

    @Override
    public List<EnrollmentDto> findAll() {
        return null;
    }

    @Override
    public EnrollmentDto findById(String id) {
        return null;
    }

    @Override
    public boolean add(EnrollmentDto entity) {
        return false;
    }

    @Override
    public boolean update(EnrollmentDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public List<CourseDto> findAllCourseInCartByUserId(ObjectId userId){
        List<ObjectId> idsCourseInCart = enrollmentRepository.findIdsOfCourseInCart(userId);
        List<CourseDto> dtos = new ArrayList<>();
        for (ObjectId id:
                idsCourseInCart ) {
            Course course = courseRepository.findById(id).get();
            course.getCategory().setCourses(null);
            course.setTargets(targetRepository.findBy_courseId(course.get_id()));
            List<Video> videoDtos = videoRepository.findBy_courseId(course.get_id());
            course.setLecturerCount(videoDtos.size());
            int lectureCount = 0;
            for (Video
                    videoDto : videoDtos
            ) {
                lectureCount += videoDto.getLength();
            }
            course.setLengthVideos(lectureCount);
            course.setVideos(videoDtos);
            course.setStudents(enrollmentRepository.findStudentsInCourse(course.get_id()));
            course.setTargets(null);
            course.setVideos(null);
            dtos.add(new CourseDto(course));
        }
        return dtos;
    }

    @Override
    public List<CourseDto> findAllBuyedCourseByUserId(ObjectId userId) {
        List<ObjectId> idsCourseInCart = enrollmentRepository.findIdsOfBuyedCourse(userId);
        List<CourseDto> dtos = new ArrayList<>();
        for (ObjectId id:
                idsCourseInCart ) {
            Course course = courseRepository.findById(id).get();
            course.getCategory().setCourses(null);
            course.setTargets(targetRepository.findBy_courseId(course.get_id()));
            List<Video> videoDtos = videoRepository.findBy_courseId(course.get_id());
            course.setLecturerCount(videoDtos.size());
            int lectureCount = 0;
            for (Video
                    videoDto : videoDtos
            ) {
                lectureCount += videoDto.getLength();
            }
            course.setLengthVideos(lectureCount);
            course.setVideos(videoDtos);
            course.setStudents(enrollmentRepository.findStudentsInCourse(course.get_id()));
            course.setTargets(null);
            course.setVideos(null);
            dtos.add(new CourseDto(course));
        }
        return dtos;
    }
}

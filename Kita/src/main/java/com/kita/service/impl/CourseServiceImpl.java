package com.kita.service.impl;

import com.kita.entity.Course;
import com.kita.entity.Video;
import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.VideoDto;
import com.kita.repository.CourseRepository;
import com.kita.repository.EnrollmentRepository;
import com.kita.repository.TargetRepository;
import com.kita.repository.VideoRepository;
import com.kita.service.CourseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    TargetRepository targetRepository;
    @Autowired
    VideoRepository videoRepository;

    @Override
    public List<CourseDto> findAll() {


        List<Course> courses = courseRepository.findAll();
        List<CourseDto> dtos = new ArrayList<>();

        for (Course course :
                courses) {
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
    public CourseDto findById(String id) {
        ObjectId _id = new ObjectId(id);
        Course course = courseRepository.findById(_id).get();
        course.getCategory().setCourses(null);
        course.setTargets(targetRepository.findBy_courseId(_id));
        List<Video> videos = videoRepository.findBy_courseId(_id);
        course.setLecturerCount(videos.size());
        int totalVideoLength = 0;
        for (Video
                video : videos
        ) {
            totalVideoLength += video.getLength();
        }
        course.setLengthVideos(totalVideoLength);
        course.setVideos(videos);
        course.setStudents(enrollmentRepository.findStudentsInCourse(course.get_id()));

        return new CourseDto(course);

    }

    @Override
    public boolean add(CourseDto entity) {
        return false;
    }

    @Override
    public boolean update(CourseDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public List<CourseDto> findAll(Pageable pageable) {
        List<Course> courses = courseRepository.findAll(pageable).getContent();
        List<CourseDto> dtos = new ArrayList<>();
        for (Course course :
                courses) {
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
            dtos.add(new CourseDto(course));
        }


        return dtos;
    }

    public List<CourseDto> findTopCourseSeller(int numOfTop) {
        List<ObjectId> courseIds = enrollmentRepository.findTopSellerCourse(numOfTop);

        List<CourseDto> dtos = new ArrayList<>();

        for (ObjectId id :
                courseIds) {
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

    public List<CourseDto> findTopPopularCourses(int numOfTop) {
        List<ObjectId> courseIds = enrollmentRepository.findTopPopularCourse(numOfTop);

        List<CourseDto> dtos = new ArrayList<>();

        for (ObjectId id :
                courseIds) {
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
    public List<CourseDto> findTopDiscountCourses(int numOfTop) {
        List<Course> courses = courseRepository.findTopDiscount(PageRequest.of(0, numOfTop));


        List<CourseDto> dtos = new ArrayList<>();

        for (Course course :
                courses) {
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

package com.kita.service.impl;

import com.kita.entity.Category;
import com.kita.entity.Course;
import com.kita.entity.Video;
import com.kita.entity.dto.CategoryDto;
import com.kita.repository.CategoryRepository;
import com.kita.repository.EnrollmentRepository;
import com.kita.repository.TargetRepository;
import com.kita.repository.VideoRepository;
import com.kita.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mongodb.client.model.Aggregates.lookup;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    TargetRepository targetRepository;
    @Autowired
    VideoRepository videoRepository;

    public List<CategoryDto> setup() {
//        LookupOperation lookup = LookupOperation.newLookup()
//                .from("courses")
//                .localField("id")
//                .foreignField("categoryId")
//                .as("courses");
//        Aggregation aggregation = Aggregation.newAggregation(Category.class, Aggregation.match(Criteria.where("_id").is(new ObjectId("602c90c028913833b377b643"))), lookup);
////        Aggregation aggregation = Aggregation.newAggregation(lookup);
////        List<Object> ss =  mongoTemplate.aggregate(aggregation, Object.class, Object.class).getMappedResults();
//        TypedAggregation<Order> noRepeatAggregation2 =
//                Aggregation.newAggregation(Order.class,lookup);
//
//        List<Course> CategoryDto = mongoTemplate.aggregate(noRepeatAggregation2, Course.class).getMappedResults();
//

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(

                        Criteria.where("").orOperator(
                                Criteria.where("_id").is(new ObjectId("602c90c028913833b377b643")),
                                Criteria.where("_id").is(new ObjectId("602c90c028913833b377b644"))
                        )
                ),
                Aggregation.lookup(mongoTemplate.getCollectionName(Course.class),
                        "_id", "category_id", "courses")
        );

        AggregationResults<CategoryDto> results = mongoTemplate.aggregate(agg, mongoTemplate.getCollectionName(Category.class), CategoryDto.class);
        return results.getMappedResults();
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> dtos = new ArrayList<>();

        for (Category category :
                categories) {
            assert category.getCourses() != null;
            for (Course course :
                    category.getCourses()) {
                course.setStudents(enrollmentRepository.findStudentsInCourse(course.get_id()));
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
            }
            CategoryDto categoryDto = new CategoryDto(category);
            dtos.add(categoryDto);
        }

        return dtos;
    }

    @Override
    public CategoryDto findById(String id) {
        return null;
    }

    @Override
    public boolean add(CategoryDto entity) {
        return false;
    }

    @Override
    public boolean update(CategoryDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public List<CategoryDto> findTop(int numOfTop) {

        return enrollmentRepository.findTopCategories(numOfTop);
    }
}

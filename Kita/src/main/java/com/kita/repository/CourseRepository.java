package com.kita.repository;

import com.kita.entity.Course;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, ObjectId> {
    Page<Course> findByTitle(String title, Pageable pageable);

    @Query(value = "{}", sort = "{discount : -1}")
    List<Course> findTopDiscount(Pageable pageable);

}

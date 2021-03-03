package com.kita.repository;

import com.kita.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    @Aggregation(
            pipeline = {

                    "{ $lookup : \n" +
                            " { from : courses, localField : _id, foreignField : category_id, as : courses}\n" +
                            " }",
                    "{ $project : \n" +
                            "{" +
                            "courses: {_id: 1}" +
                            "}" +
                            "}\n"

            }

    )
    List<Category> findIdsCourseOfCategories();
//    @Query("{ _id: ObjectId(\"602c90c028913833b377b643\")},\n" +
//            "   { $push: { \n" +
//            "   \tcourses: { $each: [ \"a\", \"a\", \"a\" ] }\n" +
//            "   \t } }")
//    void test();

}

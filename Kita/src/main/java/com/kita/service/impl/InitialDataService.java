package com.kita.service.impl;

import com.kita.entity.Category;
import com.kita.entity.Course;
import com.kita.entity.dto.CategoryDto;
import com.kita.entity.dto.CourseDto;
import com.mongodb.BasicDBObject;
import com.mongodb.DBRef;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitialDataService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void initialCoursesInCategory() {
        Query query = new Query();
        List<Category> categories = mongoTemplate.find(query, Category.class);

        for (Category category :
                categories) {
            List<ObjectId> courseIds = findIdsCourseInCategory(category.get_id());
            System.out.println(courseIds.size());
            for (ObjectId oid :
                    courseIds) {
                query = new Query();
                query.addCriteria(Criteria.where("_id").is(category.get_id()));
                Update update = new Update();
                DBRef dbRef = new DBRef("courses", oid.toHexString());
                update.push("courses", dbRef);
                mongoTemplate.upsert(query, update, Category.class);
            }

        }


        //Modify
//        update.set("courses.0.$ref", "cc");
        //Remove
//update.pull("courses",new BasicDBObject("$ref", "cc"));
        //Add one
//update.push("courses", dbRef);
//Add all


    }

    // Find by Command
    public List<ObjectId> findIdsCourseInCategory(ObjectId categoryId) {
        List<ObjectId> ids = new ArrayList<>();
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(
                        //OR
//                        Criteria.where("").orOperator(
//                                Criteria.where("_id").is(new ObjectId("602c90c028913833b377b643")),
//                                Criteria.where("_id").is(new ObjectId("602c90c028913833b377b644"))
//                        )
                        Criteria.where("_id").is(categoryId)
                ),
                Aggregation.lookup(mongoTemplate.getCollectionName(Course.class),
                        "_id", "category_id", "courses")

        );

        AggregationResults<Category> results = mongoTemplate.aggregate(agg, "categories", Category.class);
        List<Category> categoryDto = results.getMappedResults();
        for (Course courseDto :
                results.getMappedResults().get(0).getCourses()) {
            ids.add(courseDto.get_id());
        }
        return ids;
    }

}

package com.kita.repository;

import com.kita.entity.Target;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetRepository extends MongoRepository<Target, ObjectId> {
    public List<Target> findBy_courseId(ObjectId id);
    @Aggregation(
            pipeline = {

                    "\t\t{" +
                            "$group: {        _id: \"$course_id\",   top: { $sum: 1 }     \n" +
                            "\t\t\t}   \n" +
                            "\t}",
                    "{\t\n" +
                            "      \t\t$sort : { top: -1 }\n" +
                            "      \t}"
                    ,
                    "{\t\n" +
                            "        \t$limit: ?0\n" +
                            "        }",
                    "{\n" +
                            "          \t$project: {course_id : 1}\n" +
                            "          \t}"
            }
    )
    List<ObjectId> findTopCategories(int num);
}

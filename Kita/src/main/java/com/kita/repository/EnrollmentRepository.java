package com.kita.repository;

import com.kita.entity.Category;
import com.kita.entity.Course;
import com.kita.entity.Enrollment;
import com.kita.entity.dto.CategoryDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, ObjectId> {
    @Query("{ $and:[{course_id: ?1}}, {user_id: ?2} ]}")
    Enrollment findByCourseIdAndUserId(ObjectId courseId, ObjectId userId);


    @Aggregation(
            pipeline = {

                    "{\n" +
                            "      $match: {\n" +
                            "       $and:" +
                            "[{course_id: ?0}, {is_buy : true}  ] \n" +
                            "      }\n" +
                            "    }",
                    "{\n" +
                            "      $count: \"students\"\n" +
                            "    }"

            }
    )
    int findStudentsInCourse(ObjectId courseId);


    @Aggregation(
            pipeline = {
                    "{\n" +
                            "    $graphLookup: {\n" +
                            "        from: 'courses',\n" +
                            "        startWith: '$course_id',\n" +
                            "        connectFromField: 'course_id',\n" +
                            "        connectToField: '_id',\n" +
                            "        as: 'course',\n" +
                            "        restrictSearchWithMatch: {'price' : {$gt: 0}}\n" +
                            "\n" +
                            "    }\n" +
                            "}",
                    "{$match: {course: { $size: 1}} }"
                    ,
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
    List<ObjectId> findTopSellerCourse(int num);


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
    List<ObjectId> findTopPopularCourse(int num);


    @Aggregation(
            pipeline = {
                    "{\n" +
                            "    $graphLookup: {\n" +
                            "        from: 'courses',\n" +
                            "        startWith: '$course_id',\n" +
                            "        connectFromField: 'course_id',\n" +
                            "        connectToField: '_id',\n" +
                            "        as: 'data',\n" +
                            "    }\n" +
                            "}", "{\n" +
                    "    $unwind: {\n" +
                    "        path: '$data',\n" +
                    "\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $project: {\n" +
                    "\n" +
                    "        data: '$data.category_id',\n" +
                    "        course_id: 1,\n" +
                    "        user_id: 1\n" +
                    "\n" +
                    "    }\n" +
                    "}", " {\n" +
                    "    $graphLookup: {\n" +
                    "        from: 'categories',\n" +
                    "        startWith: '$data',\n" +
                    "        connectFromField: 'data',\n" +
                    "        connectToField: '_id',\n" +
                    "        as: 'data2',\n" +
                    "\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $unwind: {\n" +
                    "        path: '$data2'\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $project: {\n" +
                    "        category: '$data2',\n" +
                    "        category_id: '$data2._id',\n" +
                    "        course_id: 1,\n" +
                    "        user_id: 1\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $group: {\n" +
                    "        _id: '$category_id',\n" +
                    "        top: {\n" +
                    "            $sum: 1\n" +
                    "        },\n" +
                    "        category: {\n" +
                    "            $first: '$category'\n" +
                    "        }\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $sort: {\n" +
                    "        top: -1\n" +
                    "    }\n" +
                    "}", "{\n" +
                    "    $project: {\n" +
                    "        id: '$category._id'," +
                    "        title: '$category.title',\n" +
                    "        icon: '$category.icon',\n" +
                    "        students: '$top'\n" +
                    "\n" +
                    "\n" +
                    "    }\n" +
                    "}",
                    "{\n" +
                            "    $limit: ?0\n" +
                            "}"
            }
    )
    List<CategoryDto> findTopCategories(int numOfTop);

    @Aggregation(
            pipeline = {
                    "{$graphLookup: {\n" +
                            "  from: 'courses',\n" +
                            "  startWith: '$course_id',\n" +
                            "  connectFromField: 'course_id',\n" +
                            "  connectToField: '_id',\n" +
                            "  as: 'course',\n" +
                            "}}",
                    " {$match: {\n" +
                            "  $and: \n" +
                            "    [\n" +
                            "       {user_id: ?0},\n" +
                            "  {is_in_cart: true}," +
                            "{is_buy: false}\n" +
                            "      ]\n" +
                            "\n" +
                            " }}",
                    " {$unwind: {\n" +
                            "  path: '$course',\n" +
                            "\n" +
                            "  }}",
                    " {$project: {\n" +
                            "  _id: '$course._id',\n" +
                            "}}"
            }
    )
    List<ObjectId> findIdsOfCourseInCart(ObjectId userId);


    @Aggregation(
            pipeline = {
                    "{$graphLookup: {\n" +
                            "  from: 'courses',\n" +
                            "  startWith: '$course_id',\n" +
                            "  connectFromField: 'course_id',\n" +
                            "  connectToField: '_id',\n" +
                            "  as: 'course',\n" +
                            "}}",
                    " {$match: {\n" +
                            "  $and: \n" +
                            "    [\n" +
                            "       {user_id: ?0},\n" +
                            "  {is_buy: true}\n" +
                            "      ]\n" +
                            "\n" +
                            " }}",
                    " {$unwind: {\n" +
                            "  path: '$course',\n" +
                            "\n" +
                            "  }}",
                    " {$project: {\n" +
                            "  _id: '$course._id',\n" +
                            "}}"
            }
    )
    List<ObjectId> findIdsOfBuyedCourse(ObjectId userId);

}

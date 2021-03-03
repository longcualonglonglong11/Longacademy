package com.kita.repository;

import com.kita.entity.Target;
import com.kita.entity.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {
    List<Video> findBy_courseId(ObjectId objectId);
}

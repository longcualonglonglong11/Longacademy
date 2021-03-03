package com.kita.entity;

import com.kita.repository.CourseRepository;
import com.kita.service.CourseService;
import com.kita.utility.constant.ObjectConstant;
import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;


@Document(collection= ObjectConstant.CATEGORY_LIST)
public class Category {
    @Id
    private ObjectId _id;
    private String title;
    private String icon;
    public Category(ObjectId id, String title, String icon) {
        this._id = id;
        this.title = title;
        this.icon = icon;
    }
    @Nullable
    @DBRef(lazy = true)
    private List<Course> courses ;

    public Category(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
    public Category(ObjectId _id, String title, String icon, List<Course> courses) {

        this.title = title;
        this.icon = icon;
    }
    public Category() {

    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", courses=" + courses +
                '}';
    }
}

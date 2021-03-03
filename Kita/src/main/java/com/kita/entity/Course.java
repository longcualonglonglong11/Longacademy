package com.kita.entity;

import com.kita.entity.dto.VideoDto;
import com.kita.utility.constant.ObjectConstant;
import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = ObjectConstant.COURSES_LIST)
public class Course {
    @Id
    private ObjectId _id;
    private String title;
    private String image;

    @Field("letures_count")

    private int lecturerCount;
    @Nullable
    @Field("length_videos")

    private int lengthVideos;
    @Field("view_count")

    private int viewCount;
    private double price;
    private int discount;
    private String description;
    private String content;
    @Indexed(name = "category_id_index")
    @Field("category_id")
    private ObjectId _categoryId;
    @Field("last_update")

    private Date lastUpdate;
    private String author;

    @DBRef(lazy = true)
    private Category category;
    private List<Target> targets;
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public Course() {
    }

    private int students;

    public Course(String title, String image, int lecturerCount, int lengthVideos, int viewCount, double price, int discount, String description, String content, Date lastUpdate, String author, int students, Category category) {
        this.title = title;
        this.image = image;
        this.lecturerCount = lecturerCount;
        this.lengthVideos = lengthVideos;
        this.viewCount = viewCount;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.content = content;
        this.lastUpdate = lastUpdate;
        this.author = author;
        this.students = students;
        this.category = category;

    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLecturerCount() {
        return lecturerCount;
    }

    public void setLecturerCount(int lecturerCount) {
        this.lecturerCount = lecturerCount;
    }

    public int getLengthVideos() {
        return lengthVideos;
    }

    public void setLengthVideos(int lengthVideos) {
        this.lengthVideos = lengthVideos;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ObjectId get_categoryId() {
        return _categoryId;
    }

    public void set_categoryId(ObjectId _categoryId) {
        this._categoryId = _categoryId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", lecturerCount=" + lecturerCount +
                ", lengthVideos=" + lengthVideos +
                ", viewCount=" + viewCount +
                ", price=" + price +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", _categoryId=" + _categoryId +
                ", lastUpdate=" + lastUpdate +
                ", author='" + author + '\'' +
                ", category=" + category +
                '}';
    }
}

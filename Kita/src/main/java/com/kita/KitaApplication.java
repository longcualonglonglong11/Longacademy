package com.kita;

import com.kita.entity.Category;
import com.kita.entity.Course;
import com.kita.repository.CategoryRepository;
import com.kita.repository.EnrollmentRepository;
import com.kita.service.CategoryService;
import com.kita.service.CourseService;
import com.kita.service.impl.InitialDataService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;

@ComponentScan(basePackages = "com.kita")
@EntityScan(basePackages = "com.kita.entity")
@SpringBootApplication
public class KitaApplication implements CommandLineRunner {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private InitialDataService initialDataService;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    public static void main(String[] args) {
        SpringApplication.run(KitaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Begin------------Begin------------Begin");
//        System.out.println(enrollmentRepository.findIdsOfBuyedCourse(new ObjectId("6030f964c0aaed29c23e726b")).size());
//categoryService.findTop(4);
//        initialDataService.initialCoursesInCategory();
//      List<Category> categories=  categoryRepository.findAll();
//        for (Category category:
//                categories) {
//            System.out.println(category.getTitle());
//            for (Course course :
//                    category.getCourses()) {
//                System.out.println(course.getTitle());
//            }
//        }
    }
}

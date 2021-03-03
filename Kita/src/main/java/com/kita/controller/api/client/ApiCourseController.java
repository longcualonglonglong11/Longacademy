package com.kita.controller.api.client;

import com.kita.entity.Course;
import com.kita.entity.Target;
import com.kita.entity.Video;
import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.TargetDto;
import com.kita.entity.dto.VideoDto;
import com.kita.service.CategoryService;
import com.kita.service.CourseService;
import com.kita.service.TargetService;
import com.kita.service.VideoService;
import com.kita.utility.constant.ObjectConstant;
import com.kita.utility.constant.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(UrlConstant.CROSS_ORIGIN_ALLOW_ALL_URL)
@RequestMapping(UrlConstant.API_PUBLIC_COURSE_URL)
public class ApiCourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping(UrlConstant.NONE_MAPPING_URL)
    public ResponseEntity get(
            @RequestParam(name = ObjectConstant.PAGE_PARAMETER) int page,
            @RequestParam(defaultValue = "6", required = false) int size) {
        return new ResponseEntity(courseService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping(UrlConstant.API_PUBLIC_BEST_SELLER_URL)
    public ResponseEntity get(
            @RequestParam(name = ObjectConstant.TOP_ITEM_PARAMETER, required = false, defaultValue = ObjectConstant.DEFAULT_PAGE_PARAMETER) int top
    ) {
        return new ResponseEntity(courseService.findTopCourseSeller(top), HttpStatus.OK);
    }
    @GetMapping(UrlConstant.API_PUBLIC_POPULAR_URL)
    public ResponseEntity getPop(
            @RequestParam(name = ObjectConstant.TOP_ITEM_PARAMETER, required = false, defaultValue = ObjectConstant.DEFAULT_PAGE_PARAMETER_2) int top
    ) {
        return new ResponseEntity(courseService.findTopPopularCourses(top), HttpStatus.OK);
    }
    @GetMapping(UrlConstant.ID_URL)
    public Object get(@PathVariable(ObjectConstant.ID) String id) {
        try {
            CourseDto courseDto = courseService.findById(id);


            return new ResponseEntity(courseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ObjectConstant.REQUEST_FAIL, HttpStatus.BAD_GATEWAY);

        }
    }
    @GetMapping(UrlConstant.API_PUBLIC_TOP_DISCOUNT_URL)
    public ResponseEntity getTopDiscount(
            @RequestParam(name = ObjectConstant.TOP_ITEM_PARAMETER, required = false, defaultValue = ObjectConstant.DEFAULT_PAGE_PARAMETER_2) int top
    ) {
        return new ResponseEntity(courseService.findTopDiscountCourses(top), HttpStatus.OK);
    }
}

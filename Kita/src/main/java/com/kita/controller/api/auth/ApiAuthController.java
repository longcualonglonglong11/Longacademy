package com.kita.controller.api.auth;

import com.kita.entity.Course;
import com.kita.entity.User;
import com.kita.entity.Video;
import com.kita.entity.dto.AuthDto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kita.entity.dto.CourseDto;
import com.kita.entity.dto.UserDto;
import com.kita.repository.CourseRepository;
import com.kita.repository.EnrollmentRepository;
import com.kita.repository.UserRepository;
import com.kita.service.EnrollmentService;
import com.kita.utility.constant.ObjectConstant;
import com.kita.utility.constant.UrlConstant;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@RestController
@CrossOrigin(UrlConstant.CROSS_ORIGIN_ALLOW_ALL_URL)
@RequestMapping(UrlConstant.API_AUTH_URL)
public class ApiAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EnrollmentService enrollmentService;
    @GetMapping(UrlConstant.NONE_MAPPING_URL)
    public Object get() {

        return new ResponseEntity("", HttpStatus.OK);

    }
    @Autowired
    UserRepository userRepository;

    @PostMapping(UrlConstant.NONE_MAPPING_URL)
    public Object login(@RequestBody AuthDto authDto) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());
            authentication = authenticationManager.authenticate(authentication);
            Date now = new Date();
            String token = Jwts.builder().setSubject(authDto.getEmail()).setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + 8640000000L))
                    .signWith(SignatureAlgorithm.HS512, ObjectConstant.SECRET_KEY)
                    .compact();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepository.findByEmail(authDto.getEmail());


            if (token != null)
                return new ResponseEntity(new UserDto(user, token, enrollmentService.findAllCourseInCartByUserId(user.get_id()), enrollmentService.findAllBuyedCourseByUserId(user.get_id())), HttpStatus.OK);
            else
                return new ResponseEntity<String>("Failed", HttpStatus.EXPECTATION_FAILED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Internal exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

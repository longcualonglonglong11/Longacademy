package com.kita.controller.api.client;

import com.kita.service.CategoryService;
import com.kita.utility.constant.ObjectConstant;
import com.kita.utility.constant.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(UrlConstant.CROSS_ORIGIN_ALLOW_ALL_URL)
@RequestMapping(UrlConstant.API_PUBLIC_CATEGORY_URL)
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity get() {
        return new ResponseEntity(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(UrlConstant.API_PUBLIC_TOP_ITEM_URL)
    public ResponseEntity get(
            @RequestParam(name = ObjectConstant.TOP_ITEM_PARAMETER, required = false, defaultValue = ObjectConstant.DEFAULT_PAGE_PARAMETER) int top

    ) {
        return new ResponseEntity(categoryService.findTop(top), HttpStatus.OK);
    }
}

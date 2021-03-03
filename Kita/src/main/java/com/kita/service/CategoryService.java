package com.kita.service;

import com.kita.entity.Category;
import com.kita.entity.dto.CategoryDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface CategoryService extends GenericService<CategoryDto, String> {
    List<CategoryDto> findTop(int numOfTop);
//
//    boolean addDto(Category category);
//
//    boolean updateDto(Category category);
//
//    Category findDtoById(ObjectId id);
}

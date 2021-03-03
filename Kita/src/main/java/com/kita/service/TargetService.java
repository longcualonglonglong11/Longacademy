package com.kita.service;

import com.kita.entity.Target;
import com.kita.entity.dto.TargetDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TargetService extends GenericService<TargetDto, String> {
    public List<TargetDto> findTargetsOfCourse(String courseId);
}

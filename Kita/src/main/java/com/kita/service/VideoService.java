package com.kita.service;

import com.kita.entity.dto.TargetDto;
import com.kita.entity.dto.VideoDto;

import java.util.List;

public interface VideoService extends GenericService<VideoDto, String> {
    List<VideoDto> findVideosOfCourse(String courseId);
}

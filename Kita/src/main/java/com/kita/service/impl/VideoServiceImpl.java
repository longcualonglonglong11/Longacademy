package com.kita.service.impl;

import com.kita.entity.Video;
import com.kita.entity.dto.VideoDto;
import com.kita.repository.VideoRepository;
import com.kita.service.VideoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoRepository videoRepository;
    @Override
    public List<VideoDto> findAll() {
        return null;
    }

    @Override
    public VideoDto findById(String id) {
        return null;
    }

    @Override
    public boolean add(VideoDto entity) {
        return false;
    }

    @Override
    public boolean update(VideoDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
    public List<VideoDto> findVideosOfCourse(String courseId) {
        List<Video> videos = videoRepository.findBy_courseId(new ObjectId(courseId));
        List<VideoDto> videoDtos = new ArrayList<>();
        for (Video video :
                videos) {
            videoDtos.add(new VideoDto(video));

        }
        return videoDtos;
    }
}

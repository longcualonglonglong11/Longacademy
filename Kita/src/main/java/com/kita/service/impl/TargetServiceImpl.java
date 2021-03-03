package com.kita.service.impl;

import com.kita.entity.Target;
import com.kita.entity.dto.TargetDto;
import com.kita.repository.TargetRepository;
import com.kita.service.TargetService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class TargetServiceImpl implements TargetService {
    @Autowired
    TargetRepository targetRepository;

    @Override
    public List<TargetDto> findAll() {
        List<Target> targets = targetRepository.findAll();
        List<TargetDto> targetDtos = new ArrayList<>();
        for (Target target :
                targets) {
            targetDtos.add(new TargetDto(target));
        }
        return targetDtos;
    }

    @Override
    public TargetDto findById(String id) {
        Target target = targetRepository.findById(new ObjectId(id)).get();

        return new TargetDto(target);
    }

    @Override
    public boolean add(TargetDto entity) {
        return false;
    }

    @Override
    public boolean update(TargetDto entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }


    @Override
    public List<TargetDto> findTargetsOfCourse(String courseId) {
        List<Target> targets = targetRepository.findBy_courseId(new ObjectId(courseId));
        List<TargetDto> targetDtos = new ArrayList<>();
        for (Target target :
                targets) {
            targetDtos.add(new TargetDto(target));

        }
        return targetDtos;
    }
}

package com.kita.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T, K> {
    List<T> findAll();

    T findById(K id);

    boolean add(T entity);

    boolean update(T entity);

    boolean deleteById(K id);
}


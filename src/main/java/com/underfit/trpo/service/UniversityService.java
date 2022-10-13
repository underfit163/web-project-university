package com.underfit.trpo.service;

import java.util.List;

public interface UniversityService<T> {
    List<T> getAll();

    T getById(Long id);

    T save(T dto);

    void delete(Long id);
}

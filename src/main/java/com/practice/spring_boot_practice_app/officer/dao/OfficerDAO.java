package com.practice.spring_boot_practice_app.officer.dao;

import com.practice.spring_boot_practice_app.officer.entity.Officer;

import java.util.List;
import java.util.Optional;

public interface OfficerDAO {

    Officer save(Officer officer);

    Optional<Officer> findById(Integer id);

    List<Officer> findAll();

    long count();

    void delete(Officer officer);

    boolean existsById(Integer id);
}

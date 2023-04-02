package com.yorha.dao;

import com.yorha.model.Mood;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodMapper {
    List<Mood> findAll();

    boolean update(Mood mood);
    Mood findById(Integer id);
}

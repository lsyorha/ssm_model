package com.yorha.service;

import com.yorha.dto.MoodDTO;
import com.yorha.model.Mood;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MoodService {
    List<MoodDTO> findAll();
//传统点赞功能
    boolean praiseMood(Integer userId,Integer moodId);
    boolean update(Mood mood);
    Mood findById(Integer id);
}

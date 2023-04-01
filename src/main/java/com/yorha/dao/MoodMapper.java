package com.yorha.dao;

import com.yorha.dto.MoodDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodMapper {
    List<MoodDTO> findAll();
}

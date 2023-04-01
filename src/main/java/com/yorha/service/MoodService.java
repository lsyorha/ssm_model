package com.yorha.service;

import com.yorha.dto.MoodDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MoodService {
    List<MoodDTO> findAll();
}

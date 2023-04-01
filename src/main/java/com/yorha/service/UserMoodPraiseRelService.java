package com.yorha.service;

import com.yorha.model.UserMoodPraiseRel;
import org.springframework.stereotype.Service;

@Service
public interface UserMoodPraiseRelService {
    boolean save(UserMoodPraiseRel userMoodPraiseRel);

}

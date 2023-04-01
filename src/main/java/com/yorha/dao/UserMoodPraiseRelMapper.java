package com.yorha.dao;

import com.yorha.model.UserMoodPraiseRel;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMoodPraiseRelMapper {
    boolean save(UserMoodPraiseRel userMoodPraiseRel);
}

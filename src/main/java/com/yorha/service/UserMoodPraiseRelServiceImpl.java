package com.yorha.service;

import com.yorha.dao.UserMoodPraiseRelMapper;
import com.yorha.model.UserMoodPraiseRel;
import org.springframework.stereotype.Service;

@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService{

    private UserMoodPraiseRelMapper userMoodPraiseRelMapper;

    public void setUserMoodPraiseRelMapper(UserMoodPraiseRelMapper userMoodPraiseRelMapper) {
        this.userMoodPraiseRelMapper = userMoodPraiseRelMapper;
    }

    @Override
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelMapper.save(userMoodPraiseRel);
    }
}

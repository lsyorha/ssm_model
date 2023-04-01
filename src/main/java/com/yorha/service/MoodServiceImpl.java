package com.yorha.service;

import com.yorha.dao.MoodMapper;
import com.yorha.dao.UserMapper;
import com.yorha.dto.MoodDTO;
import com.yorha.model.Mood;
import com.yorha.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class MoodServiceImpl implements MoodService{

    private MoodMapper moodMapper;
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public void setMoodMapper(MoodMapper moodMapper) {
        this.moodMapper = moodMapper;
    }

    @Override
    public List<MoodDTO> findAll() {
        return convertModel2DTO(moodMapper.findAll());
    }

//    Mood转为MoodDTO,用于前端展示
    private List<MoodDTO> convertModel2DTO(List<MoodDTO> moodList){
        if (CollectionUtils.isEmpty(moodList)) return Collections.EMPTY_LIST;
        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();
        for (Mood mood : moodList) {
            MoodDTO moodDTO1 = new MoodDTO();
            //mood属性
            moodDTO1.setId(mood.getId());
            moodDTO1.setContent(mood.getContent());
            moodDTO1.setPraiseNum(mood.getPraiseNum());
            moodDTO1.setPublishTime(mood.getPublishTime());
            moodDTO1.setUserId(mood.getUserId());
            //moodDTO属性 设置用户信息，找不到UserMapper的话是因为还没注册bean
            User user = userMapper.find(mood.getUserId());
            moodDTO1.setUserName(user.getName());
            moodDTO1.setUserAccount(user.getAccount());

            System.out.println(moodDTO1.toString());
            moodDTOList.add(moodDTO1);
        }
        return moodDTOList;
    }

}

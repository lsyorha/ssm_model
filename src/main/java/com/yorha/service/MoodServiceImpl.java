package com.yorha.service;

import com.yorha.dao.MoodMapper;
import com.yorha.dao.UserMapper;
import com.yorha.dao.UserMoodPraiseRelMapper;
import com.yorha.dto.MoodDTO;
import com.yorha.model.Mood;
import com.yorha.model.User;
import com.yorha.model.UserMoodPraiseRel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class MoodServiceImpl implements MoodService {

    private final MoodMapper moodMapper;
    private final UserMapper userMapper;
    private final UserMoodPraiseRelMapper userMoodPraiseRelMapper;

    public MoodServiceImpl(MoodMapper moodMapper, UserMapper userMapper, UserMoodPraiseRelMapper userMoodPraiseRelMapper,RedisTemplate redisTemplate) {
        this.moodMapper = moodMapper;
        this.userMapper = userMapper;
        this.userMoodPraiseRelMapper = userMoodPraiseRelMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<MoodDTO> findAll() {
        return convertModel2DTO(moodMapper.findAll());
    }


    @Override
    public boolean update(Mood mood) {
        return moodMapper.update(mood);
    }

    @Override
    public Mood findById(Integer id) {
        return moodMapper.findById(id);
    }

    @Override
//    处理用户点赞
    public boolean praiseMood(Integer userId, Integer moodId) {
//        保留关联
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRel.setUserId(userId);

        userMoodPraiseRelMapper.save(userMoodPraiseRel);
//        更新说说点赞数
        Mood mood = this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        this.update(mood);
        return Boolean.TRUE;
    }


    //    Mood转为MoodDTO,用于前端展示
    private List<MoodDTO> convertModel2DTO(List<Mood> moodList) {
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
            moodDTOList.add(moodDTO1);
        }
        return moodDTOList;
    }

//    Redis实现点赞功能
    private final RedisTemplate redisTemplate;
//    key规范命名：项目名称+版本模块+具体内容
    private static final String PRAISE_HASH_KEY = "demo02.mood.id.list.key";

    public boolean praiseMoodForRedis(Integer userId,Integer moodId){
//        存放到set集合中
        redisTemplate.opsForSet().add(PRAISE_HASH_KEY , moodId);
//        存放到set中
        redisTemplate.opsForSet().add(moodId,userId);
        return false;
    }

    public List<MoodDTO> findAllForRedis() {

        List<Mood> moodList = moodMapper.findAll();
        if (CollectionUtils.isEmpty(moodList)) return Collections.EMPTY_LIST;

        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();
        for (Mood mood : moodList) {
            MoodDTO moodDTO1 = new MoodDTO();
            //mood属性
            moodDTO1.setId(mood.getId());
            moodDTO1.setContent(mood.getContent());

            moodDTO1.setPublishTime(mood.getPublishTime());
            moodDTO1.setUserId(mood.getUserId());

//            总点赞数等于数据库点赞数+redis点赞数
            int sum = (int) ((mood.getPraiseNum()
                    + redisTemplate.opsForSet().size(mood.getId()).intValue()));
            moodDTO1.setPraiseNum(sum);
            //moodDTO属性 设置用户信息，找不到UserMapper的话是因为还没注册bean
            User user = userMapper.find(mood.getUserId());
            moodDTO1.setUserName(user.getName());
            moodDTO1.setUserAccount(user.getAccount());

            moodDTOList.add(moodDTO1);
        }

        return moodDTOList;
    }
}


package com.yorha.job;

import com.yorha.model.Mood;
import com.yorha.model.UserMoodPraiseRel;
import com.yorha.service.MoodService;
import com.yorha.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

//Scheduler负责管理quartz的运行环境，quartz是基于多线程的，它启动时会初始化一套线程，该线程用于执行一些预置的作业
//Trigger和jobDetail可以注册到Scheduler中，Scheduler可以将前者绑定到某一后者中，这样trigger触发时，对应的job就被执行。
//scheduler拥有一个SchedulerContext，类似于servletContext保存着Scheduler上下文信息，T和J都可以访问其中的信息
@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDbJob {
    @Resource
    private final RedisTemplate redisTemplate;
    @Resource
    private final MoodService moodService;
    @Resource
    private final UserMoodPraiseRelService userMoodPraiseRelService;
    //    key规范命名：项目名称+版本模块+具体内容
    private static final String PRAISE_HASH_KEY = "demo02.mood.id.list.key";
    public PraiseDataSaveDbJob(RedisTemplate redisTemplate, MoodService moodService, UserMoodPraiseRelService userMoodPraiseRelService) {
        this.redisTemplate = redisTemplate;
        this.moodService = moodService;
        this.userMoodPraiseRelService = userMoodPraiseRelService;
    }

//    cron = "0 * * * * *"  每分钟的第0秒执行一次
/*
second
minute
hour
day of month
month
day of week
*/
    @Scheduled(cron = "0 * * * * *")
    public void savePraiseDataToDb2(){
        System.out.println("每分钟对redis缓存做一次查询");
//        redis缓存中所有被点赞的说说id
        Set<String> moods = (Set<String>) redisTemplate.opsForSet().members(PRAISE_HASH_KEY).stream().map(Object::toString).collect(Collectors.toSet());
//        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        // 将集合中的每个元素转换为字符串类型
        // 转换为字符串类型的 Set 集合 // 转换为字符串类型的 Set 集合
        if (!CollectionUtils.isEmpty(moods)) {
            System.out.println("此次修改的说说列表有：" + moods);
            for (String moodIdStr : moods) {
                Integer moodId = Integer.valueOf(moodIdStr);
                System.out.println("修改的moodId为：\t" + moodId);
//            代码检查了Redis缓存是否为空，但是该判断条件永远不会满足。因为如果Redis缓存为空，
//            redisTemplate.opsForSet().members(moodId)会返回一个空的Set而不是null值,
//            所以正确的判断应该是 if (redisTemplate.opsForSet().members(moodId).isEmpty()) { continue; }；
                if (!redisTemplate.opsForSet().members(moodId).isEmpty()){
//                通过说说id获取所有点赞的用户id列表
                    Set<Integer> userIds = redisTemplate.opsForSet().members(moodId);
                    System.out.println("点赞来自以下用户："+userIds);
                    if (!CollectionUtils.isEmpty(userIds)){
                        //                    循环保存mood_id和user_id的关联关系到mysql数据库中
                        for (Integer userId : userIds) {
                            UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                            userMoodPraiseRel.setMoodId(moodId);
                            userMoodPraiseRel.setUserId(userId);
//                        保存用户和说说的关联关系
                            userMoodPraiseRelService.save(userMoodPraiseRel);
                        }
                        Mood mood = moodService.findById(moodId);
//                    更新说说点赞数
//                    说说点赞数等于Redis缓存的点赞数+数据库已有的点赞数
                        int sum = (int) (mood.getPraiseNum() + redisTemplate.opsForSet().size(moodId));

                        System.out.println("数据库现有的点赞数："+mood.getPraiseNum()+
                                "，redis缓存的点赞数："+redisTemplate.opsForSet().size(moodId)+
                                "，合并后点赞数为："+sum);
                        System.out.println("=================================");
                        mood.setPraiseNum(sum);
//                        更新mood，存储到数据库中
                        moodService.update(mood);
//                        清除redis缓存
                        redisTemplate.delete(moodId);
                    }
                }
            }
        }
        //        清除redis中的缓存数据
        System.out.println("清除本次创建的redis缓存");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        redisTemplate.delete(PRAISE_HASH_KEY);
    }
}

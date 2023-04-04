/*
 * @ClassName 不知道
 * @Description 呵呵
 * @Author yorha
 */

package com.yorha.mq;

import com.yorha.dto.MoodDTO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class MoodConsumer implements MessageListener {
    private static final String PRAISE_HASH_KEY = "demo02.mood.id.list.key";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {

//        从message获取说说实体
        try {
//            存到set中
            MoodDTO moodDTO = (MoodDTO) ((ActiveMQObjectMessage) message).getObject();
//            存到set中
            redisTemplate.opsForSet().add(PRAISE_HASH_KEY,moodDTO.getId());
            System.out.println("消费者》》》》》》》》》》》》用户id："+ moodDTO.getUserId()+"给说说id"+moodDTO.getUserId()+"点赞");
        } catch (JMSException e) {
            System.out.println(e);
        }
    }
}

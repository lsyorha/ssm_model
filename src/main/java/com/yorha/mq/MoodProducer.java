/*
 * @ClassName 不知道
 * @Description 呵呵
 * @Author yorha
 */

package com.yorha.mq;

import com.yorha.dto.MoodDTO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.print.attribute.standard.Destination;

@Component
public class MoodProducer {
    @Resource
    private final JmsTemplate jmsTemplate;

    public MoodProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

/*    @Test
    public void t1(){
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            jmsTemplate.send((session -> {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("first message"+finalI);
                return textMessage;
            }));
        }
    }*/
    public void sendMessage(Destination destination, final MoodDTO mood){
        System.out.println("生产者》》》》》》》"+mood.getUserId()+"给说说id"+mood.getId()+"点赞");
        jmsTemplate.convertAndSend(destination,mood);
    }
}

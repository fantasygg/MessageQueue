package rocketmq.springboot2;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者
 *
 * @author wangkai 2019/1/7
 */
@Component
public class Producer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    public void send(){
        rocketMQTemplate.convertAndSend("demo-queue","hello rocketmq spring starter");
        System.out.println("send ok");
    }
}

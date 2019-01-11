package rocketmq.springboot2;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author wangkai 2019/1/7
 */
@Component
@RocketMQMessageListener(topic = "demo-queue",consumerGroup = "demo-group")
public class Consumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        System.out.println("receive message :" + s);
    }
}

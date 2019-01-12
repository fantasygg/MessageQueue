package kafka.springboot;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author wangkai 2019/1/12
 */
@Component
public class Consumer {

    @KafkaListener(topics = {"demo-topic-springboot"})
    public void receiveMessage(String message){
        System.out.println("this is spring boot receive message:" + message );
    }

}

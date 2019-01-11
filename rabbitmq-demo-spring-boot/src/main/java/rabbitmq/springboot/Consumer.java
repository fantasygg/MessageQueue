package rabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author wangkai 2019/1/7
 */
@Component
public class Consumer {


    @RabbitListener(queues = "demo-queue")
    public void receive(String message){
        System.out.println(message);
    }
}

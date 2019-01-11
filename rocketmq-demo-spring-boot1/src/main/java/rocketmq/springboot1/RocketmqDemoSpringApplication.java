package rocketmq.springboot1;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RocketmqDemoSpringApplication {

    private static Logger log = LoggerFactory.getLogger(RocketmqDemoSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoSpringApplication.class, args);
    }



    @Value("${rocketmq.producer.groupName}")
    private String consumerGroupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String consumerNameSrvAddr;

    @Bean
    public void listener() throws MQClientException {
        log.info("开启消费者-------------------");

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroupName);

        consumer.setNamesrvAddr(consumerNameSrvAddr);

        consumer.subscribe("demo-queue", "*");

        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("rocketmq-spring:" + new String(msgs.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        log.info("rocketmq启动成功---------------------------------------");
    }

}


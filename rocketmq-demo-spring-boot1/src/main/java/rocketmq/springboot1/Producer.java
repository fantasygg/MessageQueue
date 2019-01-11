package rocketmq.springboot1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author wangkai 2019/1/7
 */
@Component
public class Producer {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    public void send() throws Exception {
        Message msg = new Message("demo-queue","",
                ("Hello RocketMQ " ).getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        defaultMQProducer.sendOneway(msg);
        System.out.println("send ok");
    }

}

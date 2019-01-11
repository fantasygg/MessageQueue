package rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Reliable synchronous transmission is used in extensive scenes,
 * such as important notification messages, SMS notification,
 * SMS marketing system, etc..
 *
 * @author wangkai 2018/12/12
 */
public class SynProducer {

    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("syn_test_group");
        // Specify name server addresses.
        //producer.setNamesrvAddr("192.168.1.162:9876");
        producer.setNamesrvAddr("47.93.25.249:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 1; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestSyn", "TagA", ("Hello RocketMQ " + i).
                    getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            // 同步发送 等待回执结果
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}

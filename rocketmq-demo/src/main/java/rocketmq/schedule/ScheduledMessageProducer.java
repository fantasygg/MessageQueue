package rocketmq.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import rocketmq.Constant;

/**
 * Scheduled messages differ from normal messages
 * in that they won’t be delivered until a provided time later
 *
 * @author wangkai 2018/12/14
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer(ScheduledConstant.GROUP_NAME);
        producer.setNamesrvAddr(Constant.NAME_SERVER_ADDR);
        // Launch producer
        producer.start();
        int totalMessagesToSend = 1;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message(ScheduledConstant.TOPIC_NAME, ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(3);
            // Send the message
            producer.send(message);
        }

        // Shutdown producer after use.
        producer.shutdown();
    }
}

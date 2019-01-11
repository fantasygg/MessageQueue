package rocketmq.broadcast;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import rocketmq.Constant;

/**
 * TODO
 *
 * @author wangkai 2018/12/14
 */
public class BroadcastProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(BroadcastConstant.GROUP_NAME);
        producer.setNamesrvAddr(Constant.NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i < 1; i++){
            Message msg = new Message(BroadcastConstant.TOPIC_NAME,
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }

}

package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 *
 * @author wangkai 2019/1/7
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.162");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("demo-queue", false, false, false, null);
        System.out.println(" [*] Waiting for messages.");


        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };

        channel.basicConsume("demo-queue", true, deliverCallback, cancelCallback);
    }
}

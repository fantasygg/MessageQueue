package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 * @author wangkai
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        final String username = ActiveMQConnection.DEFAULT_USER;
        final String password = ActiveMQConnection.DEFAULT_PASSWORD;
        final String brokerUrl = "failover:(tcp://localhost:61616)";

        //创建一个链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();
        //开启链接
        connection.start();

        //创建一个事务（这里通过参数可以设置事务的级别）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo-queue");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

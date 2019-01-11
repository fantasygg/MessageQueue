package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者demo
 *
 * @author wangkai 2019/1/7
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //ActiveMq 的默认用户名
        final String username = ActiveMQConnection.DEFAULT_USER;
        //ActiveMq 的默认登录密码
        final String password = ActiveMQConnection.DEFAULT_PASSWORD;
        //ActiveMQ 的链接地址
        final String brokerUrl = "failover:(tcp://localhost:61616)";

        //链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        //从工厂中创建一个链接
        //链接对象
        ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
        //开启链接
        connection.start();
        //创建一个事务（这里通过参数可以设置事务的级别）
        //事务管理
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        System.out.println("connect success " + connection.getClientID());
        //创建一个消息队列
        Queue queue = session.createQueue("demo-queue");
        //消息生产者
        MessageProducer messageProducer = session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //创建一条消息
        TextMessage msg = session.createTextMessage("hello world");
        //发送消息
        messageProducer.send(msg);
        System.out.println("send ok:" + msg);
        //提交事务
        session.commit();

        messageProducer.close();
        session.close();
        connection.close();
    }
}

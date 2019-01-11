package activemq.springboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * 消费者
 *
 * @author wangkai 2019/1/7
 */
@Component
public class Consumer {

    @JmsListener(destination = "demo-queue")
    public void receive(TextMessage message) throws JMSException {
        System.out.println(message.getText());
    }
}

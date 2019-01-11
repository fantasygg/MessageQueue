package activemq.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author wangkai 2019/1/7
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(){
        jmsMessagingTemplate.convertAndSend("demo-queue","hello spring jms");
    }

}

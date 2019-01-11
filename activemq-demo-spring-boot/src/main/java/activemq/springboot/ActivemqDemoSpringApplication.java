package activemq.springboot;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class ActivemqDemoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqDemoSpringApplication.class, args);
    }


    @Bean
    public JmsMessagingTemplate jmsTemplate(){
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("admin","admin","localhost:61616");

        JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate();
        jmsMessagingTemplate.setConnectionFactory(connectionFactory);

        return jmsMessagingTemplate;
    }

}


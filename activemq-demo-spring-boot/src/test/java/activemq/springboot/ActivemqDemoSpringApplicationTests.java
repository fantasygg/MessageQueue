package activemq.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqDemoSpringApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;

    @Test
    public void sendTest(){
        producer.send();
        System.out.println("send ok");
    }

}


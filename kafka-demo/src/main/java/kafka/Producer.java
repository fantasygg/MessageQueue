package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者
 *
 * @author wangkai 2019/1/12
 */
public class Producer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.162:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        KafkaProducer<String, String>  producer = new KafkaProducer<String, String>(properties);

        int messageNo = 1;
        final int count = 10;
        while (messageNo <= count){
            String data = "hello world" + messageNo;
            producer.send(new ProducerRecord<String, String>("demo-topic",data));
            System.out.println("send ok");
            messageNo ++;
        }
    }
}

package ecommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
	
public class NewOrderMain {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties());
		String value = "111111,222222,33333";
		ProducerRecord<String, String> record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER",  value, value);
		producer.send(record, (data, ex) -> {
			if(ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("SUCESSO: " + data.topic() + "\nPARTITION: " + data.partition() + "\nOFFSET: " + data.offset() +  "\nTIME: " + data.timestamp());			
		}).get();
	}
	
	private static Properties properties() throws IOException {
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream(
					"src/main/resources/producer.properties");
			properties.load(file);
			
			
			return properties;
	}
}
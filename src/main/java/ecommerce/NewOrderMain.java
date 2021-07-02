package ecommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import ecommerce.constant.TopicoEnum;
import ecommerce.producer.KafkaDispatcher;
	
public class NewOrderMain {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties());
		KafkaDispatcher kafkaDispatcher = new KafkaDispatcher();
		
		String value = "111111,222222,33333";
		String email = "Thanks you we are processing your order!";
		
		for (int i = 0; i < 100; i++) 
					kafkaDispatcher.send(TopicoEnum.ECOMMERCE_NEW_ORDER.getTopico(), email, value);
	}
	
	private static Properties properties() throws IOException {
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream(
					"src/main/resources/producer.properties");
			properties.load(file);
				
			return properties;
	}
}

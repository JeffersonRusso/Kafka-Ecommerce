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
	
public class NewOrderMain {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties());
		
		String value = "111111,222222,33333";
		String email = "Thanks you we are processing your order!";
		
		for (int i = 0; i < 100; i++) {
			
		String uuid = "12345" + UUID.randomUUID().toString();
			
		ProducerRecord<String, String> record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER",  uuid, value);
		ProducerRecord<String, String> emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL",  uuid, email);
		
		
		Callback callback = (data, ex) -> {
			if(ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("\n_________________________________________________________" 
					+ "\nSUCESSO: " + data.topic() + "\nPARTITION: " + data.partition() + "\nOFFSET: " + data.offset() +  "\nTIME: " + data.timestamp());			
		};
		
		producer.send(record, callback).get();
		producer.send(emailRecord, callback).get();
		}
	}
	
	private static Properties properties() throws IOException {
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream(
					"src/main/resources/producer.properties");
			properties.load(file);
			
			
			return properties;
	}
}

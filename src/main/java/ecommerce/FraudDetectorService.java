package ecommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class FraudDetectorService {
	
	public static void main(String[] args) throws IOException {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	
			if(!records.isEmpty()) {
				records.forEach(record -> 
				System.out.println("PROCESSING NEW ORDER, CHEKING FOR FRAUD\n" +
						"\nKEY: " +record.key() +
						"\nVALUE: " +record.value() +
						"\nPARTITION: " + record.partition() +
						"\nOFFSET: " + record.offset())
				);
			}
		}		
	}
	
	private static Properties properties() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream(
				"src/main/resources/consumer.properties");
		properties.load(file);
			
		return properties;
	}
}
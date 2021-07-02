package ecommerce.producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaDispatcher {
	
	private KafkaProducer<String, String> producer;
	
	public KafkaDispatcher() throws IOException {
		this.producer = new KafkaProducer<String, String>(properties());
	}
	
	public void send(String nomeTopico, String key, String value) throws InterruptedException, ExecutionException {
		
		key = key + UUID.randomUUID().toString();
		
		ProducerRecord<String, String> record = new ProducerRecord<>(nomeTopico,  key, value);
		
		Callback callback = (data, ex) -> {
			if(ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("\n_________________________________________________________" 
					+ "\nSUCESSO: " + 
					data.topic() + "\nPARTITION: " + 
					data.partition() + "\nOFFSET: " + 
					data.offset() +  "\nTIME: " + 
					data.timestamp());			
		};	
		producer.send(record, callback).get();
	}
	
	private static Properties properties() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream(
				"src/main/resources/producer.properties");
		properties.load(file);
		
		return properties;
	}
}
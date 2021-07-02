package ecommerce.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaService {

	private final KafkaConsumer<String, String> consumer;
	private final ConsumerFunction parse;
	
	public KafkaService(String topico, ConsumerFunction parse, String groupId) throws IOException {
		this.parse = parse;
		this.consumer = new KafkaConsumer<>(properties(groupId));
		consumer.subscribe(Collections.singletonList(topico));	
	}
	
	public void run() {
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			if (!records.isEmpty()) {
				for (ConsumerRecord<String, String> record : records) {
					this.parse.consume(record);
				}
			}
		}
	}
	
	private static Properties properties(String groupId ) throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/consumer.properties");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
		properties.load(file);

		return properties;
	}
}
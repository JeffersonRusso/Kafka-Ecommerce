package ecommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class LogService {
	public static void main(String[] args) throws IOException {
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties());
		consumer.subscribe(Pattern.compile("ECOMMERCE.*"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

			if (!records.isEmpty()) {
				records.forEach(record -> System.out.println("LOG\n" + 
						"\nKEY: " + record.key() +  
						"\nVALUE: "+ record.value() + 
						"\nPARTITION: " + record.partition() + 
						"\nOFFSET: " + record.offset()  +"\n")
				);
			}
		}
	}

	private static Properties properties() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/consumer.properties");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());
		properties.load(file);

		return properties;
	}
}
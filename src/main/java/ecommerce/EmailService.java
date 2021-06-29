package ecommerce;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import ecommerce.service.KafkaService;

public class EmailService {
	public static void main(String[] args) throws IOException {
	
		EmailService emailService = new EmailService();
		KafkaService kafkaService = new KafkaService("ECOMMERCE_SEND_EMAIL",
				emailService::parse);
		
		kafkaService.run();
	}
		private void parse(ConsumerRecord<String,String> record) {
				System.out.println("SEND EMAIL\n" + 
						"\nKEY: " + record.key() + 
						"\nVALUE: "+ record.value() + 
						"\nPARTITION: " + record.partition() + 
						"\nOFFSET: " + record.offset());
		}
}
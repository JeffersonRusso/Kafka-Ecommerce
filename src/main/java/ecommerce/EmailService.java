package ecommerce;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import ecommerce.constant.GroupIdEnum;
import ecommerce.constant.TopicoEnum;
import ecommerce.service.KafkaService;

/**
 * Servico kafka de EMAIL
 * @author Jefferson Russo
 *
 */

public class EmailService {
	public static void main(String[] args) throws IOException {
	
		EmailService emailService = new EmailService();
		KafkaService kafkaService = new KafkaService(TopicoEnum.ECOMMERCE_SEND_EMAIL.getTopico(),
				emailService::parse, GroupIdEnum.EMAIL_GROUP.getNomeDoGrupo());
		
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
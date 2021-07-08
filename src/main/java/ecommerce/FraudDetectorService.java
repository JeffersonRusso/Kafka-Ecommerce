package ecommerce;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import ecommerce.constant.GroupIdEnum;
import ecommerce.constant.TopicoEnum;
import ecommerce.service.KafkaService;

public class FraudDetectorService {
	
	public static void main(String[] args) throws IOException  {
		
		FraudDetectorService fraudDetectorService = new FraudDetectorService();
	
		try (KafkaService kafkaService = new KafkaService(TopicoEnum.ECOMMERCE_NEW_ORDER.getTopico(),
					fraudDetectorService::parse, 
					GroupIdEnum.FRAUD_DETECTOR_GROUP.getNomeDoGrupo())) {
						kafkaService.run();
				}
		}
	
		private void parse(ConsumerRecord<String,String> record) {
			System.out.println("PROCESSING NEW ORDER, CHEKING FOR FRAUD\n" +
					"\nKEY: " +record.key() +
					"\nVALUE: " +record.value() +
					"\nPARTITION: " + record.partition() +
					"\nOFFSET: " + record.offset());
		}
}
package ecommerce;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import ecommerce.constant.GroupIdEnum;
import ecommerce.constant.TopicoEnum;
import ecommerce.service.KafkaService;

public class LogService {
	public static void main(String[] args) throws IOException {
		
		LogService logService = new LogService();
		
		try (KafkaService kafkaService = new KafkaService(Pattern.compile("ECOMMERCE.*"),
				logService::parse, GroupIdEnum.LOG_GROUP.getNomeDoGrupo())) {
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
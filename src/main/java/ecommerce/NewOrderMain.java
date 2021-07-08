package ecommerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import ecommerce.constant.TopicoEnum;
import ecommerce.producer.KafkaDispatcher;
	
public class NewOrderMain {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		try (KafkaDispatcher orderKafkaDispatcher = new KafkaDispatcher<Order>()) {
			try (KafkaDispatcher emailKafkaDispatcher = new KafkaDispatcher<String>()) {
			for (int i = 0; i < 100; i++)  {
	
				String email = "Thanks you we are processing your order!";
				String userId = UUID.randomUUID().toString();
				String orderId  = UUID.randomUUID().toString();
				BigDecimal amount = new BigDecimal( Math.random() * 5000 + 1);
				
				Order order = new Order(userId, orderId, amount);
			
				emailKafkaDispatcher.send(TopicoEnum.ECOMMERCE_SEND_EMAIL.getTopico(), userId, email);
				
				orderKafkaDispatcher.send(TopicoEnum.ECOMMERCE_NEW_ORDER.getTopico(), userId, order);
				}
			}
		}	
	}
}
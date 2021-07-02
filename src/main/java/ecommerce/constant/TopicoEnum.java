package ecommerce.constant;

public enum TopicoEnum {

	ECOMMERCE_SEND_EMAIL ("ECOMMERCE_SEND_EMAIL"),
	ECOMMERCE_NEW_ORDER("ECOMMERCE_NEW_ORDER");
	
	private String topico;
	
	TopicoEnum(String topico) {
		this.topico = topico;
	}
	
	public String getTopico() {
		return topico;
	}
}
package ecommerce.constant;

public enum GroupIdEnum {
	
	EMAIL_GROUP ("EMAIL"),
	LOG_GROUP ("LOG"),
	FRAUD_DETECTOR_GROUP ("FRAUD DETECTOR");
	
	private String nomeDoGrupo;
	
	GroupIdEnum(String nomeDoGrupo) {
		this.nomeDoGrupo = nomeDoGrupo;
	}
	
	public String getNomeDoGrupo() {
		return nomeDoGrupo;
	}
}
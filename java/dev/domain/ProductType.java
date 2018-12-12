package dev.domain;

public enum ProductType {
	Chausson("Chausson"), Baudrier("Baudrier"), Assurage("Assurage"), Accessoire("Accessoire");

	private String value;

	private ProductType(String value) {
		this.value = value;
	}

	public String getAbreviation() {
		return value;
	}
}

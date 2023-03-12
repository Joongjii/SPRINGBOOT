package com.mc.mvc.Infra.code;

public enum Role {

	USER("ROLE_USER"),
	ADMIN("ROME_ADMIN");
	
	private String grade;
	
	Role(String grade) {
		this.grade = grade;
	}
	
	public String desc() {
		return this.grade;
	}

}

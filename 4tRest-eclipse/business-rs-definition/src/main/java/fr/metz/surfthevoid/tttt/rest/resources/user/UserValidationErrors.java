package fr.metz.surfthevoid.tttt.rest.resources.user;

public enum UserValidationErrors {
	
	EMAIL_IS_MANDATORY("user.email.cannot.be.empty", "The user's email cannot be empty"),
	EMAIL_IS_ALREADY_USED("user.email.already.used", "This email is used by another user");
	
	UserValidationErrors(String code, String description){
		this.code = code;
		this.description = description;
	}
	
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
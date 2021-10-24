package com.allinone.model.security;

public class JwtResponse 
{
	private String token;
	private String type;
	private String status;
	private String message;
	private Object result; 
	
	public JwtResponse() {
		super();
	}
	public JwtResponse(String token) {
		super();
		this.token = token;
		this.type = "Bearer";
	}
	public JwtResponse(String status,String message) {
		this.status=status;
		this.message=message;
	}
	public JwtResponse(String token,String status,String message) {
		super();
		//this.result=result;
		this.token = token;
		this.type = "Bearer";
		this.status=status;
		this.message=message;
	}
	
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = "Bearer";
	}
	

}

package com.allinone.exception;

@SuppressWarnings("serial")
public class FieldRequiredException extends RuntimeException{
	
	public FieldRequiredException(String exceptionMessage)
	{
		super(exceptionMessage);
	}	

}

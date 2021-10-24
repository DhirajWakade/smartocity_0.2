package com.allinone.exception;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allinone.configuration.Response;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(FieldRequiredException.class) 
	@ResponseBody
	public ResponseEntity<Response<Object>> notFoundException(final FieldRequiredException e) {
		Response respone=new Response<>(null,"0",e.getMessage());
		return new ResponseEntity<>(respone,HttpStatus.BAD_REQUEST);
		
    }
	
//	@ExceptionHandler(NullPointerException.class) 
//	@ResponseBody
//	public ResponseEntity<Response<Object>> nullPointerException(final NullPointerException e) {
//		Response respone=new Response<>(null,"0","NullPointerException.....");
//		return new ResponseEntity<>(respone,HttpStatus.INTERNAL_SERVER_ERROR);
//		
//    }
	
	@ExceptionHandler(TransientPropertyValueException.class) 
	@ResponseBody
	public ResponseEntity<Response<Object>> TransientPropertyValueException(final TransientPropertyValueException e) {
		Response respone=new Response<>(null,"0","Server Error ");
		return new ResponseEntity<>(respone,HttpStatus.NOT_FOUND);
		
    }
	
	
}

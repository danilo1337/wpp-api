package br.com.danilo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WppException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public WppException(String message) {
        super(message);
    }
    
    public WppException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public WppException(String message, HttpStatus status) {
    	super(message);
    	this.status = status;
    }
    
    public WppException(String message, Throwable cause, HttpStatus status) {
    	super(message, cause);
    	this.status = status;
    }
    
}

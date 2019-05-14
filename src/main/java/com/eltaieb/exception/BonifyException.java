package com.eltaieb.exception;

import com.eltaieb.model.ErrorMessageCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
public class BonifyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ErrorMessageCode errorMessageCode;

	public BonifyException(ErrorMessageCode errorMessageCode) {
		super();
		this.errorMessageCode = errorMessageCode;
	}
	
	
	
	
}

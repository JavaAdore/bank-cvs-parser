package com.eltaieb.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BankModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bankName;

	private String bankIdentifier;

	public BankModel(String bankName, String bankIdentifier) {
		super();
		this.bankName = bankName;
		this.bankIdentifier = bankIdentifier;
	}
	
	
	
}

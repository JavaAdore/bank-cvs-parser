package com.eltaieb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = BankEntity.ENTITY_NAME)
@Table(name = BankEntity.TABLE_NAME)
@Data
public class BankEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENTITY_NAME = "Bank";
	public static final String TABLE_NAME = "bank";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_PK_SEQ")
	@SequenceGenerator(name = "BANK_PK_SEQ", sequenceName = "BANK_PK_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_identifier")
	private String bankIdentifier;

}

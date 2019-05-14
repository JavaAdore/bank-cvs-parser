package com.eltaieb.service.api;

import java.util.Optional;

import com.eltaieb.entity.BankEntity;
import com.eltaieb.exception.BonifyException;
import com.eltaieb.model.BankModel;

public interface BankService {

	Optional<BankEntity> findBankByIdentifier(String bankIdentifier) throws BonifyException;
	
	BankEntity addBankEntity(BankModel bankEntity);
}

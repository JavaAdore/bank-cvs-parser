package com.eltaieb.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.eltaieb.dao.JpaBankDao;
import com.eltaieb.entity.BankEntity;
import com.eltaieb.exception.BonifyException;
import com.eltaieb.model.BankModel;
import com.eltaieb.model.ErrorMessageCode;
import com.eltaieb.service.api.BankService;
import com.eltaieb.service.util.Utils;

@Service
public class BankServiceImpl implements BankService {

	private JpaBankDao jpaBankDao;
	private ModelMapper modelMapper;

	public BankServiceImpl(JpaBankDao jpaBankDao, ModelMapper modelMapper) {
		super();
		this.jpaBankDao = jpaBankDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<BankEntity> findBankByIdentifier(String bankIdentifier) throws BonifyException {
		validateBankIdentifier(bankIdentifier);

		return jpaBankDao.findBankByIdentifier(bankIdentifier);
	}

	private void validateBankIdentifier(String bankIdentifier) throws BonifyException {
		if (Utils.isEmpty(bankIdentifier)) {
			throw new BonifyException(ErrorMessageCode.BANK_IDENTIFIER_IS_NOT_PROVIDED);
		}

	}

	@Override
	public BankEntity addBankEntity(BankModel bankModel) {

		Optional<BankEntity> bankEntityOptional = jpaBankDao.findBankByIdentifier(bankModel.getBankIdentifier());
		if (!bankEntityOptional.isPresent()) {
			BankEntity bankEntity = modelMapper.map(bankModel, BankEntity.class);
			return jpaBankDao.save(bankEntity);
		}
		return bankEntityOptional.get();
	}

}

package com.eltaieb;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.eltaieb.matcher.BonifyExceptionCustomMatcher;
import com.eltaieb.exception.BonifyException;
import com.eltaieb.model.ErrorMessageCode;
import com.eltaieb.service.api.BankService;
import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankCVSParserApplicationTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
	private BankService bankService;

	@Test
	public void contextLoads() {
		log.info("content initialized successfully");
	}

	@Test
	public void test_inquire_bank_with_null_identifier() throws BonifyException {
		expectedEx.expect(BonifyException.class);
		expectedEx.expect(new BonifyExceptionCustomMatcher(ErrorMessageCode.BANK_IDENTIFIER_IS_NOT_PROVIDED));
		bankService.findBankByIdentifier(null);
		
	}
	
	
	@Test
	public void test_inquire_bank_with_empty_identifier() throws BonifyException {
		expectedEx.expect(BonifyException.class);
		expectedEx.expect(new BonifyExceptionCustomMatcher(ErrorMessageCode.BANK_IDENTIFIER_IS_NOT_PROVIDED));
		bankService.findBankByIdentifier("    ");
		
	}
}

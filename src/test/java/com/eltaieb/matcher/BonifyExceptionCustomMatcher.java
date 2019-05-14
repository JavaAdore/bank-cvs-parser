package com.eltaieb.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.eltaieb.exception.BonifyException;
import com.eltaieb.model.ErrorMessageCode;

public class BonifyExceptionCustomMatcher extends TypeSafeMatcher<BonifyException> {

	private ErrorMessageCode foundErrorCode;
	private final ErrorMessageCode expectedErrorCode;

	public BonifyExceptionCustomMatcher(ErrorMessageCode expectedErrorCode) {
		this.expectedErrorCode = expectedErrorCode;
	}

	@Override
	public void describeTo(Description description) {
		description.appendValue(foundErrorCode).appendText(" was not found instead of ").appendValue(expectedErrorCode);
	}

	@Override
	protected boolean matchesSafely(BonifyException exception) {
		foundErrorCode = exception.getErrorMessageCode();
		return foundErrorCode == expectedErrorCode;
	}
}

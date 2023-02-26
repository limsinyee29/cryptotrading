package com.practice.cryptotrading.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Sin Yee
 *
 */
public class WhiteListValidator implements ConstraintValidator<WhiteList, Object> {

	private String[] whiteList;

	/**
	 * Default constructor
	 */
	public WhiteListValidator() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(WhiteList constraintAnnotation) {
		whiteList = constraintAnnotation.value();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		if (value instanceof String) {
			for (String s : whiteList) {
				if (s.equalsIgnoreCase((String)value)) {
					return true;
				}
			}
		} 
		return false;
	}

}

package com.excilys.formation.cdb.validation;

import java.time.chrono.IsoChronology;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateFieldValidator implements
		ConstraintValidator<DateField, String> {
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(DateField annotation) {
	}

	@Override
	public boolean isValid(String field,
			ConstraintValidatorContext constraintContext) {

		if ((field == null) || field.trim().isEmpty()) {
			return true;
		}
		String pattern = messageSource.getMessage("global.datePattern", null,
				LocaleContextHolder.getLocale());
		String date = field.trim();
		if (!date.matches(pattern.replaceAll("[-./]", "\\\\1")
				.replaceFirst("\\\\1", "([-./])").replaceAll("[dyM]", "[0-9]"))) {
			return false;
		}
		return isCorrectDate(toInt(date, pattern, "yyyy"),
				toInt(date, pattern, "MM"), toInt(date, pattern, "dd"));
	}

	private boolean isCorrectDate(int year, int month, int dayOfMonth) {
		if (dayOfMonth > 28) {
			int dom = 31;
			switch (month) {
				case 2:
					dom = (IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					dom = 30;
					break;
			}
			if (dayOfMonth > dom) {
				return false;
			}
		} else if (dayOfMonth < 0) {
			return false;
		}
		if ((month < 0) || (month > 12) || (year < 1970) || (year > 2037)) {
			return false;
		}
		return true;
	}

	private int toInt(String date, String pattern, String match) {
		Matcher m = Pattern.compile(match).matcher(pattern);
		if (m.find()) {
			return Integer.parseInt(date.substring(m.start(), m.end()));
		}

		return -1;
	}
}

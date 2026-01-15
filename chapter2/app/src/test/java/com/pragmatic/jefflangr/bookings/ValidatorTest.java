package com.pragmatic.jefflangr.bookings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.pragmatic.jefflangr.bookings.Booking.BookingValidation;

public class ValidatorTest {
	BookingValidation validData = new BookingValidation() {
		@Override
		public boolean isInvalid() {
			return false;
		}

		@Override
		public String getErrorMessage() {
			return "";
		}
	};

	BookingValidation invalidData = new BookingValidation() {
		@Override
		public boolean isInvalid() {
			return true;
		}

		@Override
		public String getErrorMessage() {
			return "invalid";
		}
	};

	@Test
	void testErrorMessagesInCaseOfValidity() {
		var expectedVal = Collections.emptyList();
		var actualVal = new Validator().validate(List.of(validData));
		assertEquals(expectedVal, actualVal);
	}

	@Test
	void testErrorMessagesInCaseOfInvalidity() {
		var expectedVal = List.of(invalidData.getErrorMessage());
		var actualVal = new Validator().validate(List.of(invalidData, validData));
		assertEquals(expectedVal, actualVal);
	}
}

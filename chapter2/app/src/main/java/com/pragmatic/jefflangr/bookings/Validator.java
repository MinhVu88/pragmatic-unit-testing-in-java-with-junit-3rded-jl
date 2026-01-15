package com.pragmatic.jefflangr.bookings;

import java.util.List;

import com.pragmatic.jefflangr.bookings.Booking.BookingValidation;

public class Validator {
	public List<String> validate(List<BookingValidation> validations) {
		return validations.stream()
											.filter(BookingValidation::isInvalid)
											.map(BookingValidation::getErrorMessage)
											.toList();
	}
}

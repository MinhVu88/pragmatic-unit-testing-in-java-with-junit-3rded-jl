package com.pragmatic.jefflangr.bookings;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pragmatic.jefflangr.bookings.Booking.BookingValidation;

class BookingTest {
	Booking booking;

	static final String VALID_NAME = "Yamato Kenji";
	static final int VALID_AGE = 18;
	static final LocalDateTime VALID_DEPARTURE_DATE = LocalDateTime.now().plus(1, DAYS);
	static final String INVALID_AIRPORT_CODE = "YYZ";
  static final String VALID_AIRPORT_CODE0 = "COS";
  static final String VALID_AIRPORT_CODE1 = "PRG";
  static final List<String> VALID_ITINERARY = List.of(VALID_AIRPORT_CODE0, VALID_AIRPORT_CODE1);

	@Nested
	class ValidBooking {
		@Test
		void testIfAllBookingDataWereValid() {
			var bookingData = new Booking(VALID_NAME, VALID_AGE, VALID_DEPARTURE_DATE, VALID_ITINERARY);
			var result = bookingData.validateBookingData0();
			assertTrue(result.isEmpty());
		}
	}

	@Nested
	class InvalidBooking {
		@Test
		void testIfNameWereNull() {
			var expectedVal = List.of("passenger's name not found");

			var bookingData = new Booking(null, VALID_AGE, VALID_DEPARTURE_DATE, VALID_ITINERARY);
			var actualVal = bookingData.validateBookingData1();

			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfNameWereEmpty() {
			var expectedVal = List.of("passenger's name not found");

			var bookingData = new Booking(" ", VALID_AGE, VALID_DEPARTURE_DATE, VALID_ITINERARY);
			var actualVal = bookingData.validateBookingData0();

			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfAgeWereBelow18() {
			var expectedVal = List.of("passenger's age must be over 18");

			var bookingData = new Booking(VALID_NAME, 17, VALID_DEPARTURE_DATE, VALID_ITINERARY);
			var actualVal = bookingData.validateBookingData1();

			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfDepartureDateWereBeforeNow() {
			var expectedVal = List.of("departure date must be later than now");

			var invalidDepartureTime = LocalDateTime.now().minus(1, SECONDS);
			var bookingData = new Booking(VALID_NAME, VALID_AGE, invalidDepartureTime, VALID_ITINERARY);
			var actualVal = bookingData.validateBookingData0();

			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfItineraryContainedEnoughAirportCodes() {
			var expectedVal = List.of("itinerary needs 2+ airport codes");

			var bookingData = new Booking(
				VALID_NAME, 
				VALID_AGE, 
				VALID_DEPARTURE_DATE, 
				List.of(VALID_AIRPORT_CODE0)
			);
			var actualVal = bookingData.validateBookingData1();

			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfItineraryContainedInvalidAirportCodes() {
			var expectedVal = List.of("itinerary contains invalid airport code(s)");

			var bookingData = new Booking(
				VALID_NAME, 
				VALID_AGE, 
				VALID_DEPARTURE_DATE, 
				List.of(VALID_AIRPORT_CODE0, INVALID_AIRPORT_CODE)
			);
			var actualVal = bookingData.validateBookingData0();

			assertEquals(expectedVal, actualVal);
		}
	}

	@Test
	void testValidationsPassedToValidator() {
		var bookingData = new Booking("", 25, null, null);
		
		final var validationsInValidator = new ArrayList<>();
		final var expectedVal = List.of("a");

		var actualVal = bookingData.validateBookingData2(new Validator() {
			@Override
			public List<String> validate(List<BookingValidation> validations) {
				validationsInValidator.addAll(validations);
				return expectedVal;
			}
		});

		assertEquals(expectedVal, actualVal);
	}
}

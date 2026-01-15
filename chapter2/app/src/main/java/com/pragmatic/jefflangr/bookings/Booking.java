package com.pragmatic.jefflangr.bookings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public record Booking(
	String name,
	int age,
	LocalDateTime departureDate,
	List<String> itinerary
) {
	private static final Set<String> AIRPORT_CODES = Set.of("COS", "DEN", "DUB", "PRG");
	
	interface BookingValidation {
		boolean isInvalid();
		String getErrorMessage();
	}

	public List<String> validateBookingData0() {
		var errorMessages = new ArrayList<String>();

		if(name == null || name.trim().isEmpty()) {
			errorMessages.add("passenger's name not found");
		}

		if(age < 18) {
			errorMessages.add("passenger's age must be over 18");
		}

		if(!departureDate.isAfter(LocalDateTime.now())) {
			errorMessages.add("departure date must be later than now");
		}

		if(itinerary.size() < 2) {
			errorMessages.add("itinerary needs 2+ airport codes");
		}

		if(!itinerary.stream().allMatch(airportCode -> AIRPORT_CODES.contains(airportCode))) {
			errorMessages.add("itinerary contains invalid airport code(s)");
		}

		return errorMessages;
	}

	class RequiredName0 implements BookingValidation {
		@Override
		public boolean isInvalid() {
			return name == null || name.trim().isEmpty();
		}

		@Override
		public String getErrorMessage() {
			return "passenger's name not found";
		}
	}

	class MinAge0 implements BookingValidation {
		@Override
		public boolean isInvalid() {
			return age < 18;
		}

		@Override
		public String getErrorMessage() {
			return "passenger's age must be over 18";
		}
	}

	class DepartureDate0 implements BookingValidation {
		@Override
		public boolean isInvalid() {
			return !departureDate.isAfter(LocalDateTime.now());
		}

		@Override
		public String getErrorMessage() {
			return "departure date must be later than now";
		}
	}

	class MinAirportCodes0 implements BookingValidation {
		@Override
		public boolean isInvalid() {
			return itinerary.size() < 2;
		}

		@Override
		public String getErrorMessage() {
			return "itinerary needs 2+ airport codes";
		}
	}

	class AirportCodes0 implements BookingValidation {
		@Override
		public boolean isInvalid() {
			return !itinerary.stream().allMatch(airportCode -> AIRPORT_CODES.contains(airportCode));
		}

		@Override
		public String getErrorMessage() {
			return "itinerary contains invalid airport code(s)";
		}
	}

	public List<String> validateBookingData1() {
		return asList(
			new RequiredName0(),
			new MinAge0(),
			new DepartureDate0(),
			new MinAirportCodes0(),
			new AirportCodes0()
		).stream()
		 .filter(BookingValidation::isInvalid)
		 .map(BookingValidation::getErrorMessage)
		 .toList();
	}

	class RequiredName1 implements BookingValidation {
		private final Booking booking;

		public RequiredName1(Booking booking) {
			this.booking = booking;
		}

		@Override
		public boolean isInvalid() {
			return booking.name == null || booking.name.trim().isEmpty();
		}

		@Override
		public String getErrorMessage() {
			return "passenger's name not found";
		}
	}

	class MinAge1 implements BookingValidation {
		private final Booking booking;

		public MinAge1(Booking booking) {
			this.booking = booking;
		}

		@Override
		public boolean isInvalid() {
			return booking.age < 18;
		}

		@Override
		public String getErrorMessage() {
			return "passenger's age must be over 18";
		}
	}

	class DepartureDate1 implements BookingValidation {
		private final Booking booking;

		public DepartureDate1(Booking booking) {
			this.booking = booking;
		}

		@Override
		public boolean isInvalid() {
			return !booking.departureDate.isAfter(LocalDateTime.now());
		}

		@Override
		public String getErrorMessage() {
			return "departure date must be later than now";
		}
	}

	class MinAirportCodes1 implements BookingValidation {
		private final Booking booking;

		public MinAirportCodes1(Booking booking) {
			this.booking = booking;
		}

		@Override
		public boolean isInvalid() {
			return booking.itinerary.size() < 2;
		}

		@Override
		public String getErrorMessage() {
			return "itinerary needs 2+ airport codes";
		}
	}

	class AirportCodes1 implements BookingValidation {
		private final Booking booking;

		public AirportCodes1(Booking booking) {
			this.booking = booking;
		}

		@Override
		public boolean isInvalid() {
			return !booking.itinerary.stream().allMatch(airportCode -> AIRPORT_CODES.contains(airportCode));
		}

		@Override
		public String getErrorMessage() {
			return "itinerary contains invalid airport code(s)";
		}
	}

	@SuppressWarnings("null")
	public List<String> validateBookingData2(Validator validator) {
		return validator.validate(
			asList(
				new RequiredName1(this),
				new MinAge1(this),
				new DepartureDate1(this),
				new MinAirportCodes1(this),
				new AirportCodes1(this) 
			)
		);
	}
}

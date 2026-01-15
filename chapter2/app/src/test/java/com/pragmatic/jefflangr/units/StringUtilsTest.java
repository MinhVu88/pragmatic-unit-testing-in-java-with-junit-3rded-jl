package com.pragmatic.jefflangr.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {
	@Nested
	class Capitalize {
		@Test
		void testCapitalizingAnEmptyString() {
			var expectedVal = "";
			var actualVal = StringUtils.capitalize("");
			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testCapitalizingALowercaseLetter() {
			var expectedVal = "X";
			var actualVal = StringUtils.capitalize("x");
			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testCapitalizingFirstLetterOfALowercaseWord() {
			var expectedVal = "Fsociety";
			var actualVal = StringUtils.capitalize("fsociety");
			assertEquals(expectedVal, actualVal);
		}

		@Test
		void testIfRemainingLettersWereLowercase() {
			var expectedVal = "Fsociety";
			var actualVal = StringUtils.capitalize("FSOCIETY");
			assertEquals(expectedVal, actualVal);
		}
	}
}

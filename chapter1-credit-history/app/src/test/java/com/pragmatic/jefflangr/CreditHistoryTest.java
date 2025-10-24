package com.pragmatic.jefflangr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditHistoryTest {
	@Test 
	void testNothing() {}

	@Test
	void testZeroRatings() {
		var creditHistory = new CreditHistory();
		var actualVal = creditHistory.getArithmeticMean();
		int expectedVal = 0;
		assertEquals(expectedVal, actualVal); 
	}
}

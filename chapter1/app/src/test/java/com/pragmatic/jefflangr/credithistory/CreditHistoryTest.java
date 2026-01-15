package com.pragmatic.jefflangr.credithistory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class CreditHistoryTest {
	CreditHistory creditHistory;

	public CreditHistoryTest() {
		System.out.println("CreditHistoryTest()");
	}

	@BeforeEach
	void init() {
		creditHistory = new CreditHistory();
		System.out.println("init()");
	}

	@Test 
	void testNothing() {}

	@Test
	void testAddingZeroCreditRatings() {
		// var creditHistory = new CreditHistory();
		// var actualVal = creditHistory.getArithmeticMean();
		// int expectedVal = 0;
		// assertEquals(expectedVal, actualVal);
		
		assertThrows(
			IllegalStateException.class, 
			() -> creditHistory.getArithmeticMean()
		);
		
		System.out.println("testAddingZeroCreditRatings()"); 
	}

	@Test
	void testAddingOneCreditRating() {
		// step 1 (arrangement): Arrange the system so that it’s in a useful state.
		// in this case: creating a CreditHistory object & adding a credit rating to it. 
		int expectedRating = 780;
		// var creditHistory = new CreditHistory();
		creditHistory.collect(new CreditRating(expectedRating));

		// step 2 (action): Act upon the system so as to create the behavior you’re trying to test.
		var actualRating = creditHistory.getArithmeticMean();
		
		// step 3 (assertion): Assert/verify that the system behaves the way you expect.
		assertEquals(expectedRating, actualRating);

		System.out.println("testAddingOneCreditRating()");
	}

	@Test
	void testAddingMultipleCreditRatings() {
		creditHistory.collect(new CreditRating(780));
		creditHistory.collect(new CreditRating(800));
		creditHistory.collect(new CreditRating(820));

		assertEquals(800, creditHistory.getArithmeticMean());
	}
}

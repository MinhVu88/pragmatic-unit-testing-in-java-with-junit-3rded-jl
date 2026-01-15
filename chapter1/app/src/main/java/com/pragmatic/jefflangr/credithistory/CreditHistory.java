package com.pragmatic.jefflangr.credithistory;

import java.util.ArrayList;
import java.util.List;

public class CreditHistory {
	private final List<CreditRating> ratings = new ArrayList<>();

	public void collect(CreditRating rating) {
		this.ratings.add(rating);
	}

	public int getArithmeticMean() {
		if(this.ratings.isEmpty()) {
			// return 0;
			throw new IllegalStateException();
		}

		var total = this.ratings.stream().mapToInt(CreditRating::rating).sum();
		return total / this.ratings.size();
	}
}

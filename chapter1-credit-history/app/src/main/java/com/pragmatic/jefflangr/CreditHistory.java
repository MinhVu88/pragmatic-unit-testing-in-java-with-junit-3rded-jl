package com.pragmatic.jefflangr;

import java.util.ArrayList;
import java.util.List;

public class CreditHistory {
	private final List<CreditRating> ratings = new ArrayList<>();

	public void collect(CreditRating rating) {
		this.ratings.add(rating);
	}

	public int getArithmeticMean() {
		if(this.ratings.isEmpty()) {
			return 0;
		}

		var total = this.ratings.stream().mapToInt(CreditRating::rating).sum();
		return total / this.ratings.size();
	}
}

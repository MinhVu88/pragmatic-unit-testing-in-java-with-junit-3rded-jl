package com.pragmatic.jefflangr;

import java.util.ArrayList;
import java.util.List;

public class CreditHistory {
	private final List<CreditRating> ratings = new ArrayList<>();

	public void collect(CreditRating score) {
		ratings.add(score);
	}

	public int getArithmeticMean() {
		var total = ratings.stream().mapToInt(CreditRating::score).sum();
		return total / ratings.size();
	}
}

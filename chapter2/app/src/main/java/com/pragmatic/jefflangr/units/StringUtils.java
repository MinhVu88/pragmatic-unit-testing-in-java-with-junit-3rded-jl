package com.pragmatic.jefflangr.units;

public class StringUtils {
	static String capitalize(String word) {
		if(word.isEmpty()) return "";
		var firstLetter = word.substring(0, 1);
		var remainingLetters = word.substring(1);
		return firstLetter.toUpperCase() + remainingLetters.toLowerCase();
	}
}

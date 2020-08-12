package com.advenoh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComparatorPlayer {
	private String name;
	private int score;

	public static int compareByScoreThenName(ComparatorPlayer lhs, ComparatorPlayer rhs) {
		if (lhs.getScore() == rhs.getScore()) {
			return lhs.getName().compareTo(rhs.getName());
		} else {
			return lhs.getScore() - rhs.getScore();
		}
	}
}

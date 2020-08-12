package com.advenoh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComparablePlayer implements Comparable<ComparablePlayer> {
	private String name;
	private int score;

	@Override
	public int compareTo(ComparablePlayer o) {
		return o.getScore() - this.getScore();
	}
}

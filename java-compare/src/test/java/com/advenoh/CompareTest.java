package com.advenoh;

import com.advenoh.model.ComparablePlayer;
import com.advenoh.model.ComparatorPlayer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareTest {

	/**
	 * https://www.daleseo.com/java-comparable-comparator/
	 */
	@Test
	public void comparableTest() {
		List<ComparablePlayer> players = new ArrayList<>();
		players.add(new ComparablePlayer("Alice", 899));
		players.add(new ComparablePlayer("Bob", 982));
		players.add(new ComparablePlayer("Chloe", 1090));
		players.add(new ComparablePlayer("Dale", 982));
		players.add(new ComparablePlayer("Eric", 1018));

		Collections.sort(players);

		assertThat(players).isSorted();
	}

	@Test
	public void comparatorTest() {
		List<ComparatorPlayer> players = new ArrayList<>();
		players.add(new ComparatorPlayer("Alice", 899));
		players.add(new ComparatorPlayer("Bob", 982));
		players.add(new ComparatorPlayer("Chloe", 1090));
		players.add(new ComparatorPlayer("Dale", 982));
		players.add(new ComparatorPlayer("Eric", 1018));

		Comparator<ComparatorPlayer> comparator = new Comparator<ComparatorPlayer>() {
			@Override
			public int compare(ComparatorPlayer a, ComparatorPlayer b) {
				return b.getScore() - a.getScore();
			}
		};

		Collections.sort(players, comparator);

		assertThat(players).isSortedAccordingTo(comparator);
	}
}

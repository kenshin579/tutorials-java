package com.advenoh;

import com.advenoh.model.ComparablePlayer;
import com.advenoh.model.ComparatorPlayer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CompareTest {
	List<ComparablePlayer> comparablePlayers;

	@Before
	public void setUp() {
		comparablePlayers = new ArrayList<>();
		comparablePlayers.add(new ComparablePlayer("Alice", 899));
		comparablePlayers.add(new ComparablePlayer("Bob", 982));
		comparablePlayers.add(new ComparablePlayer("Chloe", 1090));
		comparablePlayers.add(new ComparablePlayer("Dale", 982));
		comparablePlayers.add(new ComparablePlayer("Eric", 1018));
	}

	/**
	 * https://www.daleseo.com/java-comparable-comparator/
	 */
	@Test
	public void comparableTest() {
		Collections.sort(comparablePlayers);

		assertThat(comparablePlayers).isSorted();
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

	@Test
	public void streamComparableSort() {
		List<ComparablePlayer> sortedPlayers = comparablePlayers.stream()
				.sorted((a, b) -> b.getScore() - a.getScore())
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSorted();
	}

	@Test
	public void sortByScoreThenName() {
		List<ComparatorPlayer> players = new ArrayList<>();
		players.add(new ComparatorPlayer("Chloe", 1090));
		players.add(new ComparatorPlayer("Dale", 982));
		players.add(new ComparatorPlayer("Alice", 899));
		players.add(new ComparatorPlayer("Bob", 982));
		players.add(new ComparatorPlayer("Eric", 1018));

		List<ComparatorPlayer> sortedPlayers = players.stream()
				//.sorted((a, b) -> ComparatorPlayer.compareByNameThenScore(a, b))
				.sorted(ComparatorPlayer::compareByScoreThenName)
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSortedAccordingTo(ComparatorPlayer::compareByScoreThenName);

	}
}

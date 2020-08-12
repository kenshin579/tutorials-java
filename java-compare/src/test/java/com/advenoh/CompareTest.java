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
	List<ComparatorPlayer> comparatorPlayers;

	@Before
	public void setUp() {
		comparablePlayers = new ArrayList<>();
		comparablePlayers.add(new ComparablePlayer("Dale", 982));
		comparablePlayers.add(new ComparablePlayer("Chloe", 1090));
		comparablePlayers.add(new ComparablePlayer("Alice", 899));
		comparablePlayers.add(new ComparablePlayer("Bob", 982));
		comparablePlayers.add(new ComparablePlayer("Eric", 1018));

		comparatorPlayers = new ArrayList<>();
		comparatorPlayers.add(new ComparatorPlayer("Dale", 982));
		comparatorPlayers.add(new ComparatorPlayer("Bob", 982));
		comparatorPlayers.add(new ComparatorPlayer("Eric", 1018));
		comparatorPlayers.add(new ComparatorPlayer("Chloe", 1090));
		comparatorPlayers.add(new ComparatorPlayer("Alice", 899));
	}

	/**
	 * https://www.daleseo.com/java-comparable-comparator/
	 */
	@Test
	public void comparable_test() {
		Collections.sort(comparablePlayers);

		assertThat(comparablePlayers).isSorted();
	}

	@Test
	public void comparator_test() {
		Comparator<ComparatorPlayer> comparator = new Comparator<ComparatorPlayer>() {
			@Override
			public int compare(ComparatorPlayer a, ComparatorPlayer b) {
				return b.getScore() - a.getScore();
			}
		};

		Collections.sort(comparatorPlayers, comparator);

		assertThat(comparatorPlayers).isSortedAccordingTo(comparator);
	}

	@Test
	public void comparator_lambda_Test() {
		Comparator<ComparatorPlayer> comparator = (a, b) -> b.getScore() - a.getScore();

		Collections.sort(comparatorPlayers, comparator);

		assertThat(comparatorPlayers).isSortedAccordingTo(comparator);
	}

	@Test
	public void stream_comparable_sort() {
		List<ComparablePlayer> sortedPlayers = comparablePlayers.stream()
				.sorted((a, b) -> b.getScore() - a.getScore())
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSorted();
	}

	@Test
	public void sort_by_score_then_name() {
		List<ComparatorPlayer> sortedPlayers = comparatorPlayers.stream()
				//.sorted((a, b) -> ComparatorPlayer.compareByNameThenScore(a, b))
				.sorted(ComparatorPlayer::compareByScoreThenName)
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSortedAccordingTo(ComparatorPlayer::compareByScoreThenName);

	}

	@Test
	public void sort_extracted_comparators_test() {
		List<ComparatorPlayer> sortedPlayers = comparatorPlayers.stream()
//				.sorted(Comparator.comparing(a -> a.getScore()))
				.sorted(Comparator.comparing(ComparatorPlayer::getScore))
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSortedAccordingTo(Comparator.comparing(ComparatorPlayer::getScore));
	}

	@Test
	public void thenComparing_test() {
		List<ComparatorPlayer> sortedPlayers = comparatorPlayers.stream()
				.sorted(Comparator.comparing(ComparatorPlayer::getScore)
						.thenComparing(ComparatorPlayer::getName))
				.collect(Collectors.toList());

		assertThat(sortedPlayers).isSortedAccordingTo(ComparatorPlayer::compareByScoreThenName);
	}
}

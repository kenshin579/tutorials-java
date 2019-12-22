package com.advenoh.vod.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum VodCollectionType {
	RECOMMENDATION,
	CURATION;

	private static final List<VodCollectionType> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));

	public static VodCollectionType getRandomType()  {
		return VALUES.get(new Random().nextInt(VALUES.size()));
	}

}

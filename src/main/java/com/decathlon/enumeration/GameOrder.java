package com.decathlon.enumeration;

public enum GameOrder {
	HUNDRED_MTS(1, ScoringTables.HUNDRED_MTS),
	LONGJUMP(2, ScoringTables.LONG_JUMP),
	SHOTPUT(3, ScoringTables.SHOT_PUT),
	HIGHJUMP(4, ScoringTables.HIGH_JUMP),
	FOUR100MTS(5, ScoringTables.FOUR_HUNDRED_MTS),
	HURDLESMTS110(6, ScoringTables.HURDLES_MTS_110),
	DISCUSTHROW(7, ScoringTables.DISCUS_THROW),
	POLEVAULT(8, ScoringTables.POLE_VAULT),
	JAVELINTHROW(9, ScoringTables.JAVELIN_THROW),
	THOUSAN500MTS(10, ScoringTables.THOUSAND_FIVE_HUNDRED_MTS);

	private final int order;
	private final ScoringTables scoringTables;

	/**
	 * @param order int
	 * @param scoringTables ScoringTables
	 */
	GameOrder(int order, ScoringTables scoringTables) {
		this.order = order;
		this.scoringTables = scoringTables;
	}

	public int getOrder() {
		return order;
	}


	public ScoringTables getScoringTable() {
		return scoringTables;
	}


}

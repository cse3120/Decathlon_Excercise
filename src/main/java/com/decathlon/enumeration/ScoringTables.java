package com.decathlon.enumeration;


import com.decathlon.DecathlonResult;
import com.decathlon.utils.DecathlonUtils;

import java.math.BigDecimal;

public enum ScoringTables implements PointsCalculation{

	HUNDRED_MTS("100 Meters", new BigDecimal("25.4347"), new BigDecimal("18"), new BigDecimal("1.81")){
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.trackEvents(DecathlonUtils.getSecond(value), this);
		}
	},
	LONG_JUMP("Long Jump", new BigDecimal("0.14354"), new BigDecimal("220"), new BigDecimal("1.4")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(DecathlonUtils.getCentimeters(value), this);
		}
	},
	SHOT_PUT("Shot Put", new BigDecimal("51.39"), new BigDecimal("1.5"), new BigDecimal("1.05")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(DecathlonUtils.getMeters(value), this);
		}
	},
	HIGH_JUMP("High Jump", new BigDecimal("0.8465"), new BigDecimal("75"), new BigDecimal("1.42")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(DecathlonUtils.getCentimeters(value), this);
		}
	},
	FOUR_HUNDRED_MTS("400 mts", new BigDecimal("1.53775"), new BigDecimal("82"), new BigDecimal("1.81")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.trackEvents(DecathlonUtils.getSecond(value), this);
		}
	},
	HURDLES_MTS_110("Hurdles 110 mts", new BigDecimal("5.74352"), new BigDecimal("28.5"), new BigDecimal("1.92")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.trackEvents(DecathlonUtils.getSecond(value), this);
		}
	},
	DISCUS_THROW("Discus Throw", new BigDecimal("12.91"), new BigDecimal("4"), new BigDecimal("1.1")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(new BigDecimal(value), this);
		}
	},
	POLE_VAULT("Pole Vault", new BigDecimal("0.2797"), new BigDecimal("100"), new BigDecimal("1.35")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(DecathlonUtils.getCentimeters(value), this);
		}
	},
	JAVELIN_THROW("Javalin Throw", new BigDecimal("10.14"), new BigDecimal("7"), new BigDecimal("1.08")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			return decathlonResult.fieldEvents(DecathlonUtils.getMeters(value), this);
		}
	},
	THOUSAND_FIVE_HUNDRED_MTS("1500 mts", new BigDecimal("0.03768"), new BigDecimal("480"), new BigDecimal("1.85")) {
		@Override
		public BigDecimal calculatePoints(String value) {
			DecathlonResult decathlonResult = new DecathlonResult();
			if(value.contains(":")){
				value=value.replace(":",".");
			}
			return decathlonResult.trackEvents(DecathlonUtils.getMinuteToSecond(value), this);
		}
	};
	private final String name;
	private final BigDecimal valueA;
	private final BigDecimal valueB;
	private final BigDecimal valueC;


	/**
	 * @param name String.
	 * @param valueA BigDecimal
	 * @param valueB BigDecimal
	 * @param valueC BigDecimal
	 */
	ScoringTables(String name, BigDecimal valueA, BigDecimal valueB, BigDecimal valueC) {
		this.name = name;
		this.valueA = valueA;
		this.valueB = valueB;
		this.valueC = valueC;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getValueA() {
		return valueA;
	}

	public BigDecimal getValueB() {
		return valueB;
	}

	public BigDecimal getValueC() {
		return valueC;
	}
}
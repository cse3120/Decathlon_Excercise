package com.decathlon;

import com.decathlon.enumeration.ScoringTables;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class ScoringTablesTest {

	@DisplayName("Verify the calculate of Score to Mts 100 Event")
	@Test
	void verifyScoreMTS100() {
		BigDecimal scoreCalculate = ScoringTables.HUNDRED_MTS.calculatePoints("12.61");
		assertEquals(new BigDecimal("536"), scoreCalculate);

	}

	@DisplayName("Verify the calculate of Score to Long Jump Event")
	@Test
	void verifyScoreLONGJUMP() {
		BigDecimal scoreCalculate = ScoringTables.LONG_JUMP.calculatePoints("5.00");
		assertEquals(new BigDecimal("382"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Shot Put Event")
	@Test
	void verifyScoreSHOTPUT() {
		BigDecimal scoreCalculate = ScoringTables.SHOT_PUT.calculatePoints("9.22");
		assertEquals(new BigDecimal("439"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to High Jump Event")
	@Test
	void verifyScoreHIGHJUMP() {
		BigDecimal scoreCalculate = ScoringTables.HIGH_JUMP.calculatePoints("1.50");
		assertEquals(new BigDecimal("389"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Mts 400 Event")
	@Test
	void verifyScoreMTS400() {
		BigDecimal scoreCalculate = ScoringTables.FOUR_HUNDRED_MTS.calculatePoints("60.39");
		assertEquals(new BigDecimal("400"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Hurdles Mts 110 Event")
	@Test
	void verifyScoreHURDLESMTS110() {
		BigDecimal scoreCalculate = ScoringTables.HURDLES_MTS_110.calculatePoints("16.43");
		assertEquals(new BigDecimal("685"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Discus Throw Event")
	@Test
	void verifyScoreDISCUSTHROW() {
		BigDecimal scoreCalculate = ScoringTables.DISCUS_THROW.calculatePoints("21.60");
		assertEquals(new BigDecimal("302"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Pole Vault Event")
	@Test
	void verifyScorePOLEVAULT() {
		BigDecimal scoreCalculate = ScoringTables.POLE_VAULT.calculatePoints("2.60");
		assertEquals(new BigDecimal("264"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Javelin Throw Event")
	@Test
	void verifyScoreJAVELINTHROW() {
		BigDecimal scoreCalculate = ScoringTables.JAVELIN_THROW.calculatePoints("35.81");
		assertEquals(new BigDecimal("382"), scoreCalculate);
	}

	@DisplayName("Verify the calculate of Score to Mts 1500 Event")
	@Test
	void verifyScoreMTS1500() {
		BigDecimal scoreCalculate = ScoringTables.THOUSAND_FIVE_HUNDRED_MTS.calculatePoints("5.25.72");
		assertEquals(new BigDecimal("421"), scoreCalculate);
	}
}

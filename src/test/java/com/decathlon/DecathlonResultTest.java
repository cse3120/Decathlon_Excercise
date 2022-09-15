package com.decathlon;

import com.decathlon.model.Athlete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecathlonResultTest {

	DecathlonResult decathlonResult;

	@BeforeEach
	void init() {
		this.decathlonResult = new DecathlonResult();
	}

	@DisplayName("Verify the method result event")
	@Test
	void verifyMethodResultEvent() {
		List<String> rows = new ArrayList<String>();
		rows.add("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72");
		rows.add("Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76");
		List<Athlete> athletes = this.decathlonResult.resultEvent(rows);
		assertNotNull(athletes);
		assertEquals(2, rows.size());
	}

	@DisplayName("Verify the method result event")
	@Test
	void verifyOneObjecEvent() {
		List<String> rows = new ArrayList<String>();
		rows.add("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72");
		List<Athlete> athletes = this.decathlonResult.resultEvent(rows);
		assertNotNull(athletes);
		assertEquals(1, rows.size());
		assertEquals("John Smith" ,athletes.get(0).getName());
		assertEquals(new BigDecimal("4200.00"),athletes.get(0).getTotalScore());
		assertEquals(new BigDecimal("536"),athletes.get(0).getEvent().get(0).getScore());
	}
}

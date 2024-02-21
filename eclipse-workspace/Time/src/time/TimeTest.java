package time;

import org.junit.Assert;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly",
			seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("23:59:59");
		assertTrue("The seconds were not calculated properly",
			seconds== 86399);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getTotalSeconds("10:00");});
	}
	
	//getSeconds
	@ParameterizedTest
	@ValueSource(strings = { "00:00:00", "23:10:50" })
	void testGetSecondsBoundaryGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		if (candidate == "00:00:00") {
				assertTrue("The seconds were not calculated properly",
					seconds==0);	
		}
		if (candidate == "23:10:50") {
			assertTrue("The seconds were not calculated properly",
					seconds == 50);
		}
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getSeconds("aaaaaaaaaaaaa");});
	}


	//GetTotalMinutes
	@ParameterizedTest
	@ValueSource(strings = { "01:20:00" })
	void testGetTotalMinutesGoodBoundary(String candidate) {
	int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly",
			minutes == 20);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getTotalMinutes("10 twenty");});
	}

	
	@ParameterizedTest
	@ValueSource(strings = { "23:59:59", "23:10:50",
	"23:05:10" })
	void testGetTotalHoursGoodBoundary(String candidate) {
	int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly",
			hours == 23);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getTotalHours("two:zero");});
	}
	
	@Test
	void testGetMillisecondsGood() {
		int milliseconds = Time.getMilliseconds("12:05:05:30");
		assertTrue("The milliseconds were not calculated properly",
				milliseconds == 30);
	}
	
	@Test
	void testGetMillisecondsBoundary() {
		int milliseconds = Time.getMilliseconds("12:05:05:59");
		assertTrue("The milliseconds were not calculated properly",
				milliseconds == 59);
	}

	@Test
	void testGetMillisecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getMilliseconds("10:00:00");});
	}
}


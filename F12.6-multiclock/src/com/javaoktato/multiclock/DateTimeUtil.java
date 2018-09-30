/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.multiclock;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateTimeUtil {
	private Duration stopperAccumulated = Duration.ZERO;
	private Instant stopperStarted = null;
	private List<String> stopperLaps = new ArrayList<>();
	private boolean stopperRunning = false;

	private Duration timerDuration = Duration.ZERO;
	private Instant timerStarted = null;

	private DateTimeFormatter df;

	public DateTimeUtil() {
		DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
		dtfb.appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':')
				.appendValue(ChronoField.MINUTE_OF_HOUR, 2)
				.appendLiteral(':')
				.appendValue(ChronoField.SECOND_OF_MINUTE, 2)
				.appendLiteral('.')
				.appendValue(ChronoField.MILLI_OF_SECOND, 3);
		df = dtfb.toFormatter();
	}

	private String formatDuration(Duration d) {
		LocalTime zeroTime = LocalTime.of(0, 0, 0, 0);
		return formatTime(zeroTime.plus(d));
	}

	private String formatPeriod(Period p) {
		StringBuffer sb = new StringBuffer();
		sb.append(p.getYears());
		sb.append("y ");
		sb.append(p.getMonths());
		sb.append("m ");
		sb.append(p.getDays());
		sb.append("d");
		return sb.toString();
	}

	private String formatTime(LocalTime t) {
		return df.format(t);
	}

	public String getCurrentTimeByTimeZone(String zoneId) {
		return formatTime(LocalTime.now(ZoneId.of(zoneId)));
	}

	public String getZeroTime() {
		return formatDuration(Duration.ZERO);
	}

	public void startStopper() {
		stopperStarted = Instant.now();
		stopperRunning = true;
	}

	public void stopStopper() {
		stopperAccumulated = stopperAccumulated.plus(Duration.between(stopperStarted, Instant.now()));
		stopperRunning = false;
	}

	public void resetStopper() {
		stopperAccumulated = Duration.ZERO;
		stopperRunning = false;
		stopperLaps.clear();
	}

	public void lapStopper() {
		if (stopperRunning)
			stopperLaps.add(getCurrentStopperDuration());
	}

	public String getCurrentStopperDuration() {
		Duration sinceStarted = Duration.between(stopperStarted, Instant.now());
		return formatDuration(stopperAccumulated.plus(sinceStarted));
	}

	public List<String> getStopperLaps() {
		return Collections.unmodifiableList(stopperLaps);
	}

	public void startTimer(String d) {
		LocalTime timerTime = LocalTime.parse(d);
		timerDuration = Duration.between(LocalTime.of(0, 0, 0, 0), timerTime);
		timerStarted = Instant.now();
	}

	public void stopTimer() {
		Duration elapsed = Duration.between(timerStarted, Instant.now());
		timerDuration = timerDuration.minus(elapsed);
	}

	public void resumeTimer() {
		timerStarted = Instant.now();
	}

	public String getCurrentTimerDuration() {
		Duration elapsed = Duration.between(timerStarted, Instant.now());
		Duration current = timerDuration.minus(elapsed);
		if (current.isNegative())
			current = Duration.ZERO;
		return formatDuration(current);
	}

	public boolean isTimerOff() {
		Duration elapsed = Duration.between(timerStarted, Instant.now());
		Duration current = timerDuration.minus(elapsed);
		return current.isNegative();
	}
	
	public String getDuration(int y1, int m1, int d1, int y2, int m2, int d2) {
		LocalDate first = LocalDate.of(y1, m1, d1);
		LocalDate second = LocalDate.of(y2, m2, d2);
		return formatPeriod(first.until(second));
	}
}

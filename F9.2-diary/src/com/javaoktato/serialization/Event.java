/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.serialization;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DATETIME_FORMAT = "YYYY/MM/dd HH:mm";
	private static final DateTimeFormatter dateTimeFormat =
			DateTimeFormatter.ofPattern(DATETIME_FORMAT);

	private String name;
	private String location;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public int compareTo(Event e) {
		if (e == null)
			return -1;
		else if ((e.startDate == null) && (this.startDate != null))
			return -1;
		else if ((e.startDate == null) && (this.startDate == null))
			return 0;

		int startDateCmp = startDate.compareTo(e.startDate);
		if (startDateCmp != 0)
			return startDateCmp;

		if ((e.endDate == null) && (this.endDate != null))
			return -1;
		else if ((e.endDate == null) && (this.endDate == null))
			return 0;
		return endDate.compareTo(e.endDate);
	}

	@Override
	public String toString() {
		return name + " (" + location + ") " + dateTimeFormat.format(startDate) + " - "
				+ dateTimeFormat.format(endDate);
	}
}
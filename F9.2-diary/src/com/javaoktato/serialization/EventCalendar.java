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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;

public class EventCalendar {

	private static final String DATETIME_FORMAT = "uuuu/MM/dd HH:mm";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader reader = new BufferedReader(isr);) {

			Set<Event> events = new TreeSet<>();
			DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

			for (;;) {
				System.out.println("1 - Adatok betöltése");
				System.out.println("2 - Események listája");
				System.out.println("3 - Új esemény felvitele");
				System.out.println("4 - Adatok mentése");
				System.out.println("5 - Kilépés");

				String input = reader.readLine();
				switch (input) {
				case "1":
					System.out.println("Fájlnév:");
					input = reader.readLine();
					File inputFile = new File(input);
					try (FileInputStream fis = new FileInputStream(inputFile);
							ObjectInputStream ois = new ObjectInputStream(fis);) {
						events = (Set<Event>) ois.readObject();
					}
					break;
				case "2":
					for (Event e : events)
						System.out.println(e);
					break;
				case "3":
					Event e = new Event();
					System.out.println("Esemény neve:");
					e.setName(reader.readLine());
					System.out.println("Esemény helye:");
					e.setLocation(reader.readLine());
					System.out.println("Esemény kezdésének ideje (pl. 2013/08/02 17:00)");
					LocalDateTime dt1 = LocalDateTime.parse(reader.readLine(), dateTimeFormat);
					e.setStartDate(dt1);
					System.out.println("Esemény befejezésének ideje (pl. 2013/08/02 18:00)");
					LocalDateTime dt2 = LocalDateTime.parse(reader.readLine(), dateTimeFormat);
					e.setEndDate(dt2);
					events.add(e);
					break;
				case "4":
					System.out.println("Fájlnév:");
					input = reader.readLine();
					File outputFile = new File(input);
					try (FileOutputStream fos = new FileOutputStream(outputFile);
							ObjectOutputStream oos = new ObjectOutputStream(fos);) {
						oos.writeObject(events);
					}
					break;
				case "5":
					return;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Érvénytelen fájlformátum.");
			e.printStackTrace();
		} catch (DateTimeParseException e1) {
			System.out.println("Érvénytelen dátum.");
			e1.printStackTrace();
		}
	}
}

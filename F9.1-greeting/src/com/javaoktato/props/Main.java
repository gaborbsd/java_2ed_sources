/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.props;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

public class Main {
	private static final String PROPSFILE = "greeting.properties";
	private static final String PROP_NAME = "Name";
	private static final String PROP_LASTSEEN = "LastSeen";

	private static String name = null;
	private static long lastSeen;

	public static void loadSettings() {
		Properties props = new Properties();
		File file = new File(PROPSFILE);
		try {
			if (!file.exists())
				file.createNewFile();
			// beállítások betöltése
			try (FileInputStream fis = new FileInputStream(file);) {
				props.load(fis);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// név beolvasása
		name = props.getProperty(PROP_NAME);

		// utolsó látogatás óta eltelt idő milliszekundumokban
		String lastSeenStr = props.getProperty(PROP_LASTSEEN);
		if (lastSeenStr != null)
			lastSeen = Long.valueOf(lastSeenStr);
	}

	public static void saveSettings() {
		Properties props = new Properties();
		File file = new File(PROPSFILE);
		try {
			if (!file.exists())
				file.createNewFile();
			if (name != null)
				props.setProperty(PROP_NAME, name);
			if (lastSeen != 0)
				props.setProperty(PROP_LASTSEEN, Long.toString(lastSeen));

			try (FileOutputStream fos = new FileOutputStream(file);) {
				props.store(fos, "Greeting program configuration");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		for (;;) {
			System.out.println("1 - Üdvözlet");
			System.out.println("2 - Név beállítása");
			System.out.println("3 - Konfiguráció betöltése");
			System.out.println("4 - Konfiguráció mentése");
			System.out.println("5 - Kilépés");

			try {
				String input = reader.readLine();
				switch (input) {
				case "1":
					StringBuffer greeting = new StringBuffer("Üdvözlöm");
					if (name != null) {
						greeting.append(", ");
						greeting.append(name);
					}
					greeting.append("!");
					System.out.println(greeting.toString());
					long now = new Date().getTime();
					if (lastSeen > 0) {
						long hours = (now - lastSeen) / 360000;
						System.out.println("Több, mint " + hours + " órája nem láttam!");
					}
					lastSeen = now;
					break;
				case "2":
					System.out.println("Adja meg a nevét:");
					name = reader.readLine();
					break;
				case "3":
					loadSettings();
					break;
				case "4":
					saveSettings();
					break;
				case "5":
					return;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

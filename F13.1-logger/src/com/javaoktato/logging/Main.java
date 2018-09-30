/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.logging;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Main {
	private static final Logger logger = Logger.getLogger("logging");

	public static void main(String args[]) {
		boolean consoleLog = false;

		logger.setUseParentHandlers(false);
		logger.setLevel(Level.FINEST);
		Handler h;
		try {
			h = new FileHandler("application.log");
		} catch (SecurityException | IOException e1) {
			h = new ConsoleHandler();
			consoleLog = true;
		}
		h.setLevel(Level.FINEST);
		h.setFormatter(new XMLFormatter());
		logger.addHandler(h);
		if (consoleLog)
			logger.warning("Nem lehet a naplófájlt inicializálni, "
					+ "a napló a konzolra kerül.");

		logger.entering("Main", "main");

		String name = "";

		logger.info("A konfiguráció beolvasása");
		Properties props = new Properties();
		File f = new File("settings.properties");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			props.load(new FileReader(f));
			name = props.getProperty("Name", "Névtelen felhasználó");
		} catch (IOException e) {
			logger.severe("Nem sikerült a konfigurációt olvasni.");
			e.printStackTrace();
		}
		logger.config("A konfiguráció beolvasva.");

		logger.fine("A felhasználó üdvözlése");
		System.out.println("Üdvözlöm " + name + '!');
		logger.exiting("Main", "main");
	}
}

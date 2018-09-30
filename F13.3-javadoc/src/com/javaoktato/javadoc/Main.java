/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.javadoc;

import java.util.Calendar;

import javax.swing.JOptionPane;

/**
 * Ez a program főosztálya.
 * 
 * @author Kövesdán Gábor
 * 
 */
public class Main {

	/**
	 * A metódus a <em>napszaknak megfelelő</em> üdvözletet készít a megadott
	 * felhasználó számára. Az időt {@link Calendar} objektum segítségével adjuk
	 * meg. Ha a név <code>null</code>, akkor kimarad a megszólítás.
	 * 
	 * @param time
	 *            az aktuális idő.
	 * @param name
	 *            a felhasználó neve.
	 * @return a személyre szabott üdvözlő szöveg.
	 * 
	 * @throws NullPointerException
	 *             ha az idő helyett <code>null</code>t adunk meg.
	 * 
	 * @see Calendar
	 */
	public static String greet(Calendar time, String name) {
		int hours = time.get(Calendar.HOUR_OF_DAY);
		StringBuffer buf = new StringBuffer();

		if ((hours > 4) && (hours < 12))
			buf.append("Jó reggelt kívánok");
		else if (hours < 18)
			buf.append("Jó napot kívánok");
		else
			buf.append("Jó estét kívánok");

		if (name != null) {
			buf.append(", ");
			buf.append(name);
		}
		buf.append('!');
		return buf.toString();
	}

	/**
	 * A program belépési pontja.
	 * 
	 * @param args
	 *            Parancssori argumentumok. Az első argumentumban adható meg a
	 *            felhasználó neve.
	 */
	public static void main(String[] args) {
		String name = args.length > 0 ? args[0] : null;
		String greet = greet(Calendar.getInstance(), name);
		JOptionPane.showMessageDialog(null, greet);
	}
}

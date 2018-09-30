/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.locale;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class Main {
	private static final String GOOD_MORNING = "GoodMorning";
	private static final String GOOD_AFTERNOON = "GoodAfternoon";
	private static final String GOOD_EVENING = "GoodEvening";
	private static final String CURRENT_DATE = "CurrentDate";
	private static final String TODAY_IS = "TodayIs";
	private static final String CURRENT_TIME = "CurrentTime";
	private static final String EUR_PRICE = "EurPrice";
	private static final String EUR_PRICE_VAL = "EurPriceVal";

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

		LocalDateTime dateTime = LocalDateTime.now();

		if (dateTime.getHour() < 10)
			System.out.println(bundle.getString(GOOD_MORNING));
		else if (dateTime.getHour() < 18)
			System.out.println(bundle.getString(GOOD_AFTERNOON));
		else
			System.out.println(bundle.getString(GOOD_EVENING));

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
		System.out.println(MessageFormat.format(bundle.getString(CURRENT_DATE), dateFormatter.format(dateTime)));

		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("eeee");
		System.out.println(MessageFormat.format(bundle.getString(TODAY_IS), dayFormatter.format(dateTime)));

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		System.out.println(MessageFormat.format(bundle.getString(CURRENT_TIME), timeFormatter.format(dateTime)));

		if (bundle.containsKey(EUR_PRICE_VAL)) {
			String currPrice = bundle.getString(EUR_PRICE_VAL);
			double price = Double.parseDouble(currPrice);
			System.out.println(MessageFormat.format(bundle.getString(EUR_PRICE), price));
		}
	}
}

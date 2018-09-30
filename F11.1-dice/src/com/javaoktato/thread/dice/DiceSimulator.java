/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.dice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DiceSimulator implements Runnable {
	private Map<Integer, Integer> results = new HashMap<>();
	private int turns;

	public DiceSimulator(Map<Integer, Integer> results, int turns) {
		this.results = results;
		this.turns = turns;
	}

	@Override
	public void run() {
		Random rnd = new Random();
		while (turns > 0) {
			int result = rnd.nextInt(6) + 1; // 1..6
			
			// Szükséges a szinkronizáció, próbáljuk ki nélküle!
			synchronized (results) {
				int occurrences = results.get(result);
				occurrences++;
				results.put(result, occurrences);
			}
			turns--;
		}
	}
}

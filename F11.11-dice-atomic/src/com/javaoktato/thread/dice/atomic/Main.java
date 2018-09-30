/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.dice.atomic;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	private Map<Integer, AtomicInteger> results = new HashMap<>();
	
	public static void main(String[] args) {
		new Main().start();
	}

	public void start() {
		// Kezdetben minden értékhez 0 dobás
		IntStream.range(1, 7).forEach(i -> results.put(i, new AtomicInteger(0)));

		// 10 szálon dobjunk darabonként 10.000-et
		List<Thread> threads = IntStream.range(0, 10).mapToObj(i -> new DiceSimulator(results, 100_000)).map(Thread::new)
				.collect(Collectors.toList());
		
		// Időmérés
		Instant startInstant = Instant.now();
		
		// Szálak indítása
		threads.forEach(Thread::start);
		
		// Várjuk be az eredményt
		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) { }
		});
		
		Instant endInstant = Instant.now();
		
		// Nézzük meg a dobásokat
		results.forEach((k, v) -> System.out.println(k + " - " + v));
		
		// Hány volt összesen?
		int totalExperiments = results.values().stream().mapToInt(i -> i.get()).sum();
		System.out.println(totalExperiments);
		
		// Mennyi időbe tellett?
		Duration elapsedTime = Duration.between(startInstant, endInstant);
		System.out.println(elapsedTime);
	}
}

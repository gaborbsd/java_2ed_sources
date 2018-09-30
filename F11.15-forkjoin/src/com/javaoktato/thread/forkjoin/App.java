/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class App {
	private static final int LIMIT = 5000;

	public static void main(String[] args) {
		int[] divisors = new int[LIMIT];

		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		DivisorNumberTask task = new DivisorNumberTask(divisors, 1, LIMIT);
		forkJoinPool.invoke(task);

		for (int i = 1; i < LIMIT; i++)
			if (divisors[i] == 2)
				System.out.println(i);
	}
}

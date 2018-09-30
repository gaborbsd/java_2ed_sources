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

import java.util.concurrent.RecursiveAction;

public class DivisorNumberTask extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private int[] results;
	private int minIdx;
	private int maxIdx;

	public DivisorNumberTask(int[] results, int minIdx, int maxIdx) {
		this.results = results;
		this.minIdx = minIdx;
		this.maxIdx = maxIdx;
	}

	private int divisorNumber(int num) {
		int result = 0;

		if (num == 1)
			return 1;
		else if (num <= 3)
			return 2;

		for (int i = 1; i < num / 2; i++)
			if (num % i == 0)
				result += 2;

		if (num % 2 == 0)
			result--;

		return result;
	}

	private void determineDivisorNumbers() {
		System.out.println(Thread.currentThread().getName() + ": " + minIdx + " - " + maxIdx);
		for (int i = minIdx; i < maxIdx; i++) {
			results[i] = divisorNumber(i);
		}
	}

	@Override
	protected void compute() {
		int length = maxIdx - minIdx;
		if (length > 50) {
			int split = length / 2;
			invokeAll(new DivisorNumberTask(results, minIdx, minIdx + split),
					new DivisorNumberTask(results, minIdx + split, maxIdx));
		} else {
			determineDivisorNumbers();
		}
	}
}

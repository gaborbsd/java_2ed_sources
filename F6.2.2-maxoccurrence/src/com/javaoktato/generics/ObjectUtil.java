/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectUtil {

	public static <T> T maxOccurrence(T[] arr) {
		Map<T, Integer> m = new HashMap<>();
		for (T t : arr) {
			Integer count = m.containsKey(t) ? m.get(t) : 0;
			m.put(t, ++count);
		}
		T max = null;
		int maxCount = 0;
		for (Entry<T, Integer> e : m.entrySet())
			if (e.getValue() > maxCount) {
				maxCount = e.getValue();
				max = e.getKey();
			}
		return max;
	}

	public static void main(String args[]) {
		Integer[] arr = new Integer[] { 1, 1, 1, 2, 2, 3, 5, 5, 5, 5, 5 };
		Integer m = maxOccurrence(arr);
		System.out.println(m);
	}
}
